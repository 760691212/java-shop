# java-shop

## system 微服务
* 系统微服务分为四个模块，分别为权限设置、平台管理、业务设置、系统管理

### 1、权限设置
*  角色管理 （创建不同管理权限的角色）

      **name  名称**
 
      **level 级别**
 
      **permission 权限**
 
      **status 状态 (启用 / 禁用)**
 
      **isDelete (true、false)**
 
      **desc  描述**
 
      **createTime 创建日期**
 
      **updateTime 修改日期**
 
*  用户管理 （创建各种用户）

      **username  用户名**
 
      **password  密码**
 
      **roles 角色**
 
      **sex  性别**
      
      **idcard  身份证**
      
      **phone  电话**
      
      **email  邮箱**
      
      **dept  单位**
      
      **job  职务**
      
      **image  头像**
 
      **isDelete (true、false) 是否删除**
 
      **createTime 创建日期**
 
      **updateTime 修改日期**
 
*  菜单管理 （创建菜单）

      **title  菜单标题**
 
      **icon  图标**
      
      **type  类型**
      
      **nodeType 节点类型 （根节点、父节点、子节点）**
      
      **path  路由地址**
      
      **pid  上级菜单**
 
      **sort  菜单排序**
      
      **permission  权限标识**
      
      **component  组件路径**
      
      **iframe  外链菜单**
      
      **cache  是否缓存**
      
      **hidden  是否可见**
 
      **isDelete (true、false) 是否删除**
 
      **createTime 创建日期**
 
      **updateTime 修改日期**
 
*  字典 (两个表)

表一
***

     title  字典名称
 
     desc  描述
       
     createTime 创建日期

     updateTime 修改日期
      
表二
***
      label  标签
      
      value 值
      
      sort  排序
      
      nodeType 节点类型 （根节点、父节点、子节点）
      
      pid  上级id
 
      desc  描述
      
      permission  权限标识
      
      createTime 创建日期
      
      updateTime 修改日期

### 2、平台管理
*  平台信息 
      
      **name  平台名称**
 
      **title 平台标题**
      
      **desc  描述**
      
      **keyword 关键词**
      
      **logo  平台Logo**
      
      **hotline  服务热线**
 
      **email  客服邮箱**
*  基本设置

  常规设置
  ***
      topSearchTerms  热门搜索关键字
      timeFormat 时间格式
      moneyFormat  货币格式
      salesReturnNum 退货上传图片凭证数
      isEvaluationAudit  是否评价审核
      defaultImage   商品默认图片
  购物设置
  ***
      isAutoTrueDelivery  是否开启自动确认收货
      isSupportInvoice  能否支持开发票
      reduceInventoryOpportunity 减库存的时机
      increaseSalesOpportunity  增加销量的时机
  备注设置
  ***
     isNoteAmendInvoiceInfo  修改订单“发票信息”时是否填写备注
     isNoteAmendConsigneeInfo  修改订单“收货人信息”时是否填写备注
     isNoteAmendGoodsInfo  修改订单“商品信息”时是否填写备注
     isNoteAmendCostInfo  修改订单“费用信息”时是否填写备注
     isNoteAmendCloseOrder  关闭订单时是否填写备注
     isNoteAmendAncellationOrder  取消订单时是否填写备注    
     isNoteAmendReturnGoods   退货处理时是否填写备注    
     isNoteAmendGetGoodsReturnGoods  确认收货后退货时是否填写备注    
     isNoteAmendRefundProcessing  退款处理时是否填写备注    
     isNoteAmendUpdateGrowthIntegral  修改积分或成长值时是否填写备注 
    
*  消息提醒(两个表)
表一 消息/标签
***
    id 消息id
    name 名称
    messageType 消息类型
    type  类型 （消息 / 标签） 
    label  标签名称
    value  值
    pid 父节点id
表二 消息模板   
***
    messageType 消息类型
    title 标题
    contentTemplate 内容模板
    
*  验证码设置

    **isEnableAuthCode 是否启用验证码**
    
    **isErroedEnableAuthCode 是否登录失败时显示验证码**
    
    **AuthCodeType 验证码方式**
    
    **AuthCodeimageWidth 图形验证码宽度**
    
    **AuthCodeimageHeight 图形验证码高度**
    
    **AuthCodeimageInfoSize 图形验证码内容大小**
    
    **AuthCodeimageNum 图形验证码位数**

### 3、业务设置
*  运费模板
*  物流公司
*  支付设置
*  区域管理

### 4、系统管理
*  操作日志

    **serialNumber   编号**
    
    **handlersId 操作者id**
    
    **handleTime 操作日期**
    
    **ipAddress ip地址**
    
    **handleLog 操作记录**
    
*  系统监控
    
    
*  数据库管理

    **fileName   备份文件名**
    
    **version 版本号**
    
    **size 大小（字节）**
    
    **backupsTime 备份时间**
