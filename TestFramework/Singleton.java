
import etu1816.framework.Scope;
import etu1816.framework.type.ScopeType;
import etu1816.framework.ModelView;
import etu1816.framework.MethodAnnotation;

@Scope(type = ScopeType.SINGLETON)
public class Singleton {
    private int test;

    @MethodAnnotation(url = "s.do")
    public ModelView save() {
        ModelView mv = new ModelView();

        Test t = new Test();
        t.setId(test);
        t.setNom("Singleton");

        mv.setView("/test.jsp");
        mv.addItem("obj", t);

        test++;

        return mv;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

}
