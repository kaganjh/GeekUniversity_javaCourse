import org.springframework.stereotype.Component;

@Component
public class Sheet {

    // 颜色
    private String color;
    // 长度
    private String length;


    private Quilt quilt;

    public String getColor() {
        return color;
    }

    public String getLength() {
        return length;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setQuilt(Quilt quilt) {
        this.quilt = quilt;
    }

    public Quilt getQuilt() {
        return quilt;
    }

    // 使用构造器注入方式将Quilt注入到Sheet,你也可以使用set方式;原则上还可以其它方法
    public Sheet(Quilt quilt) {
        this.quilt = quilt;
    }
// 省略set/get

}