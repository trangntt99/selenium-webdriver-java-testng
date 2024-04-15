package javaTester;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import zmq.io.net.Address;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Topic_01_Data_Type {
    // Kiểu dữ liệu trong Java - 2 nhoms

    // I - Kiểu dữ liệu nguyên thủy (Primitive Type)
    // 8 loại
    // Số nguyên: byte (8bit) - short (16bit) - int (32bit) - long (64bit)
    // Ko có phần thập phân: nhân viên trong 1 công ty/ học sinh trong 1 lớp/ trường/...
    byte bNumber = 40;
    short sNumber = 3000;
    int iNumber = 15635658;
    long lNumber = 234343400;

    // Số thực: float (32bit) - double (64bit)
    // Có phần thập phân: điểm số, lượng/...
    float fNumber = 9.99f;
    double dNumber = 35.800789d;

    // Kí tự: char
    // Đại diện cho 1 kí tự
    char c = 'T';
    char d = 'r';

    // Logic: boolean
    // Kết qu bài test: pass/ fail (ko ngoại lệ)
    boolean status1 = true;
    boolean status2 = false;

    // II - Kiểu dữ liệu tham chiếu (Reference Type)
    // Class
    FirefoxDriver firefoxDriver = new FirefoxDriver();
    Select select = new Select(firefoxDriver.findElement(By.className("")));
    Topic_01_Data_Type topic01 = new Topic_01_Data_Type();

    // Interface
    WebDriver driver;
    JavascriptExecutor jsExecutor;

    // Object
    Object obj = "Automation FC";

    // Array (kiểu nguyên thủy/ tham chiếu)
    int[] studentAge = {15, 20, 8};
    String[] studentName = {"Automation", "Testing"};

    // Collection: List/ Set/ Queue
    List<String> studentAddress = new ArrayList<String>();
    List<String> studentCity = new LinkedList<>();
    List<String> studentPhone = new Vector<>();

    // String - Chuỗi kí tự
    String fullName = "Nguyễn Văn Trỗi";

    // Khai báo 1 biến để lưu trữ 1 loại dữ liệu nào đó
    // Access Modifier: Phạm vi truy cập (public/ private/ protected/ default)
    // Kiểu dữ liệu
    // Tên biến
    // Giá trị của biến - gán vào với phép =
    // Nếu như không gán: dữ liệu mặc định
    public int studentNumber = 200;
}
