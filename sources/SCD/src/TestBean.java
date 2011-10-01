import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * @author harchevnikov_m
 *         Date: 26.09.11
 *         Time: 22:34
 */
@ManagedBean(name = "testBean")
@SessionScoped
public class TestBean {
    private String name="jkhg";

    public String getName() {
        return "asdad";
    }

    public void setName(String name) {
        this.name = name;
    }
}
