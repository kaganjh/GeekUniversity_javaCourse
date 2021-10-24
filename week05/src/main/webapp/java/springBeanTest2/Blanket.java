package springBeanTest2;

import org.springframework.beans.factory.annotation.Autowired;

public class Blanket {

    // 颜色
    private String color;
    // 长度
    private String length;

    // 构造器注入
    private Cotton cotton;

    public String getColor() {
        return color;
    }

    public String getLength() {
        return length;
    }

    public Cotton getCotton() {
        return cotton;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setCotton(Cotton cotton) {
        this.cotton = cotton;
    }

    @Autowired
    public Blanket(Cotton cotton){
        this.cotton = cotton;
    }
    //  省略set/get
}