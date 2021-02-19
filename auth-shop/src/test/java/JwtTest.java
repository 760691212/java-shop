import com.shop.auth.entity.UserInfo;
import com.shop.auth.utils.JwtUtils;
import com.shop.auth.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {
    private static final String pubKeyPath = "D:\\e-commerceProject\\web procedure\\shop\\management-shop\\src\\assets\\tmp\\rsa\\rsa.pub";
    private static final String priKeyPath = "D:\\e-commerceProject\\web procedure\\shop\\management-shop\\src\\assets\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTYxMzcxOTQ1Nn0.Gzfwt6kKNXMp2CvNGNeSkVS2hZzD0xwllwpKrjnZSnrtr66yJrFHjJTFq5OiEXR2yx1R4NISnKYrfRF_V4pZ79rJeyXRtDk1s1mFHW5iNnljjZGqDlsrGBrnQIwi-EgSJGvVjDpcxkeGCd94Bf4rBPfbM_7VN8r00o1Vsob7jVA";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }

}
