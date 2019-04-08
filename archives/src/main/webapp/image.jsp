<%--
  Created by IntelliJ IDEA.
  User: YangGang
  Date: 2018/10/30
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="image/jpeg"
         import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*"
         pageEncoding="GBK"%>
<%!Color getRandColor(int fc, int bc) {//给定范围获得随机颜色
    Random random = new Random();
    if (fc > 255)
        fc = 255;
    if (bc > 255)
        bc = 255;
    int r = fc + random.nextInt(bc - fc);
    int g = fc + random.nextInt(bc - fc);
    int b = fc + random.nextInt(bc - fc);
    return new Color(r, g, b);
}%>
<%
    //设置页面不缓存
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);

    // 在内存中创建图象
    // 通过这里可以修改图片大小
    int width = 85, height = 23;
    BufferedImage image = new BufferedImage(width, height,
            BufferedImage.TYPE_INT_RGB);

    // 获取图形上下文
    // g相当于笔
    Graphics g = image.getGraphics();

    //生成随机类
    Random random = new Random();

    // 设定背景色
    g.setColor(getRandColor(200, 250));
    // 画一个实心的长方，作为北京
    g.fillRect(0, 0, width, height);

    //设定字体
    g.setFont(new Font("黑体", Font.PLAIN, 18));

    //画边框
    g.setColor(Color.BLUE);
    g.drawRect(0,0,width-1,height-1);

    // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
    g.setColor(getRandColor(160, 200));
    for (int i = 0; i < 155; i++) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(12);
        int yl = random.nextInt(12);
        g.drawLine(x, y, x + xl, y + yl);
    }

    // 取随机产生的认证码(4位数字)
    //String rand = request.getParameter("rand");
    //rand = rand.substring(0,rand.indexOf("."));
    String sRand = "";
    // 如果要使用中文，必须定义字库，可以使用数组进行定义
    // 这里直接写中文会出乱码，必须将中文转换为unicode编码
    String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K",
            "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j",
            "k", "m", "n", "p", "s", "t", "u", "v", "w", "x", "y", "z",
            "1", "2", "3", "4", "5", "6", "7", "8", "9" };

    for (int i = 0; i < 4; i++) {
        String rand = str[random.nextInt(str.length)];
        sRand += rand;
        // 将认证码显示到图象中
        g.setColor(new Color(20 + random.nextInt(110), 20 + random
                .nextInt(110), 20 + random.nextInt(110)));//调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
        g.drawString(rand, 16 * i + 6, 19);
    }

    // 将认证码存入SESSION
    session.setAttribute("rand", sRand);

    // 图象生效
    g.dispose();

    // 输出图象到页面
    ImageIO.write(image, "JPEG", response.getOutputStream());
    out.clear();
    out = pageContext.pushBody();
%>
