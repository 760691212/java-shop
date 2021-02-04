package com.shop.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class NginxStart {
    /**
     * @desc 启动进程 10.158 root root
     */
    public static void startProc() {
        System.out.println("开启进程:" + "nginx.exe");

        try {
            executeCmd("D:\\e-commerceProject\\web procedure\\shop\\java-shop\\nginxStart.bat");
        } catch (IOException e) {
            System.err.println("nginx.exe" + "线程开启失败");
            e.printStackTrace();
        }
    }

    /**
     * @desc 杀死进程
     */
    public static void killProc() {
        System.out.println("关闭进程:" + "nginx.exe");
        try {
            executeCmd("taskkill /F /IM " + "nginx.exe");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("nginx.exe" + "线程关闭失败");
        }
    }

    /**
     * @desc 执行cmd命令
     */
    public static String executeCmd(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
         Process process = runtime.exec(command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder build = new StringBuilder();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            build.append(line);
        }
        return build.toString();
    }

    /**
     * @desc 执行cmd命令
     */
    public static String executeCmd2(String command) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec(command);
        // Process process = runtime.exec( command);
        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
        String line = null;
        StringBuilder build = new StringBuilder();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            build.append(line);
        }
        return build.toString();
    }

    /**
     * @desc 判断进程是否开启
     */
    public static boolean findProcess(String processName) {
        BufferedReader bufferedReader = null;
        try {
            Process proc = Runtime.getRuntime().exec("tasklist -fi " + '"' + "imagename eq " + processName + '"');
            bufferedReader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception ex) {
                }
            }
        }
    }

    private static void killLinuxProc() {
        System.out.println("关闭进程:" + "nginx.exe");
        String[] command = new String[1];
        command[0] = "pkill -9 nginx";
        System.err.println("nginx.exe" + "线程关闭失败");

    }

    /**
     * 打印进程的状态
     *
     * @param
     */
    public static void logStatus() {
        boolean flag = findProcess("nginx.exe");
        if (flag) {
            System.out.println();
            System.err.println("nginx.exe" + "进程状态：开启");
            System.out.println();
        } else {
            System.out.println();
            System.err.println("nginx.exe" + "进程状态：关闭");
            System.out.println();
        }
    }

    public static void testWindows() {

        logStatus();

        // 关闭进程
//        killProc();

//        logStatus();

        // 开启进程
        startProc();

        logStatus();
    }

    public static void testLinux() {

        // logStatus();

        // 关闭进程
        // killLinuxProc();

        // logStatus();

        // 开启进程
        startLinuxProc();

        // logStatus();
    }

    private static void startLinuxProc() {
        System.out.println("开启进程:" + "nginx");
        String command1 ="/usr/local/nginx/sbin/nginx";

        try {
            String pro = executeCmd2(command1);
            System.out.println(pro);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("nginx开启失败");
        }
    }

}
