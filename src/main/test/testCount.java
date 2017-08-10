/**
 * @author mafh
 * @create 2017-08-09 17:34
 * Created With Intellij IDEA
 */
public class testCount {
    public static void main(String[] args) {
        Integer totalNum = 1;
        Integer pageSize = 3;
        Integer pageIndex = 1;
        System.out.println((totalNum-1)/pageSize+1 < pageIndex);
    }
}
