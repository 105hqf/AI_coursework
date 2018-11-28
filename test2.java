import java.util.ArrayList;
import java.util.Arrays;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;


//  自定义一个队列的参数，内有一个F(n)的int和一个数组
class List_Elem{
    int Fresult;
    int Hresult;
    int[] list;
}
public class test2 {
    /**
     * 方发快，使用A*算法计算八数码问题
     * 方法传入两个矩阵作为参数，一个原始矩阵，一个目标矩阵
     * 通过A*算法，将原始矩阵转换成目标矩阵，如果成功，则输出最少的移动步数，如果失败，返回false
     */
    private int []Ffunction = new int[8];

    public static void main(String arge[]){
        int[] orarray = {2,8,3,1,0,4,7,6,5};
        int[] targetarray = {1,2,3,8,0,4,7,6,5};
        List_Elem Templist = new List_Elem();
        int count = 0;//  记录循环的次数，该参数可以直接作为G(n)
        //  首先找到0的位置
        int temp = 0;
        int i = 0;
        int flag = 0;
        //  主代码快
        //  将目标矩阵和初始矩阵转化为数组形式
        //  用于存储每一次移动后形成的矩阵对应的数组
        ArrayList<List_Elem> resultlist = new ArrayList<>();
        //  将第一个数组放入到队列中
        List_Elem tempelem = new List_Elem();
        tempelem.list = orarray;
        tempelem.Fresult = 4;
        tempelem.Hresult = 4;
        resultlist.add(tempelem);
        //  用于记录每一步 的所有可能性，找到最小F(n)
        ArrayList<List_Elem> treeList = new ArrayList<>();
        for(;;){
            /**
             * 通过A*算法的思想，进行遍历搜索，根据f(n)=g(n)+h(n)的形式，找出f（n）最小的方向，进行遍历
             * 定义一个f(n)，假设(n)表示矩阵中不在位的元素到目标位置的曼哈顿距离之和，G(n)表示的是已经进行的
             * 移动次数
             */
            Templist = new List_Elem();
            count = 0;
            i = 0;
            for (; ; ) {
                if (resultlist.get(resultlist.size() - 1).list[i] == 0) break;
                else i++;
            }
            //  对零位置的按上下左右进行移动，改变矩阵，找到F(N)最小的一种移动方式
            //  如果零位置上方存在元素，与之交换位置
            if (i - 3 >= 0) {
                ///  学习：Java中的arraylist不可以直接转换给其他参数修改，只能修改其中的元素
                Templist.list = new int[9];
                for (int k = 0; k < 9; k++) {
                    Templist.list[k] = Integer.valueOf(resultlist.get(resultlist.size() - 1).list[k]);
                }
                temp = resultlist.get(resultlist.size() - 1).list[i - 3];
                Templist.list[i - 3] = resultlist.get(resultlist.size() - 1).list[i];
                Templist.list[i] = temp;
                //  交换位置后，对比目标矩阵，求出曼哈顿距离H(n)的值，并且求出F(n)
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        //  找出位置不同的元素，求出距离之和
                        if (Templist.list[j] == targetarray[k]) {
                            int step = 0;
                            if (k / 3 == j / 3)
                                step = Math.abs(j - k);
                            else if (k / 3 > j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            } else if (k / 3 < j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            }
                            Templist.Fresult = Templist.Fresult + Math.abs(step) + count;
                            Templist.Hresult = Templist.Hresult + Math.abs(step);
                            break;
                        }
                    }
                }
                for(int k= 0;k<=resultlist.size()-1;k++){
                    if(Arrays.equals(Templist.list,resultlist.get(k).list))
                    {flag =1;break;}
                    //  将其存入treelist中
                }
                if(flag==0)treeList.add(Templist);
            }
            /////////////////////////////////////////
            //  如果零位置的下方位置中存在元素的话，进行下一步
            if (i + 3 <= 8) {
                Templist = new List_Elem();
                Templist.list = new int[9];
                for(int k=0;k<9;k++){
                    Templist.list[k] = Integer.valueOf(resultlist.get(resultlist.size() - 1).list[k]);
                }
                temp = resultlist.get(resultlist.size() - 1).list[i + 3];
                Templist.list[i + 3] = resultlist.get(resultlist.size() - 1).list[i];
                Templist.list[i] = temp;
                //  交换位置后，对比目标矩阵，求出曼哈顿距离H(n)的值，并且求出F(n)
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        //  找出位置不同的元素，求出距离之和
                        if (Templist.list[j] == targetarray[k]) {
                            int step = 0;
                            if (k / 3 == j / 3)
                                step = Math.abs(j - k);
                            else if (k / 3 > j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            } else if (k / 3 < j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            }
                                Templist.Fresult =Templist.Fresult+ Math.abs(step) + count;
                                Templist.Hresult = Templist.Hresult+Math.abs(step);
                                break;
                        }
                    }
                }
                for(int k= 0;k<=resultlist.size()-1;k++){
                    if(Arrays.equals(Templist.list,resultlist.get(k).list))
                    {flag =1;break;}
                    else flag = 0;
                    //  将其存入treelist中
                }
                if(flag==0)treeList.add(Templist);
            }
            /////////////////////////////////
            ///   当零元素左边存在元素时，进行下一步
            if (i / 3 == (i - 1) / 3&&i-1>=0) {
                Templist = new List_Elem();
                Templist.list = new int[9];
                for(int k=0;k<9;k++){
                    Templist.list[k] = Integer.valueOf(resultlist.get(resultlist.size() - 1).list[k]);
                }
                temp = resultlist.get(resultlist.size() - 1).list[i - 1];
                Templist.list[i - 1] = resultlist.get(resultlist.size() - 1).list[i];
                Templist.list[i] = temp;
                //  交换位置后，对比目标矩阵，求出曼哈顿距离H(n)的值，并且求出F(n)
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        //  找出位置不同的元素，求出距离之和
                        if (Templist.list[j] == targetarray[k]) {
                            int step = 0;
                            if (k / 3 == j / 3)
                                step = Math.abs(j - k);
                            else if (k / 3 > j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            } else if (k / 3 < j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            }
                                Templist.Fresult =Templist.Fresult+ Math.abs(step) + count;
                                Templist.Hresult = Templist.Hresult+Math.abs(step);
                                break;
                        }
                    }
                }
                for(int k= 0;k<=resultlist.size()-1;k++){
                    if(Arrays.equals(Templist.list,resultlist.get(k).list))
                    {flag =1;break;}
                    else flag = 0;
                    //  将其存入treelist中
                }
                if(flag==0)treeList.add(Templist);
            }
            ////////////////////////////////
            //  当零元素的右边存在元素时，进行下一步
            if (i / 3 == (i + 1) / 3&&i+1<=8) {
                Templist = new List_Elem();
                Templist.list = new int[9];
                for(int k=0;k<9;k++){
                    Templist.list[k] = Integer.valueOf(resultlist.get(resultlist.size() - 1).list[k]);
                }
                temp = resultlist.get(resultlist.size() - 1).list[i + 1];
                Templist.list[i + 1] = resultlist.get(resultlist.size() - 1).list[i];
                Templist.list[i] = temp;
                //  交换位置后，对比目标矩阵，求出曼哈顿距离H(n)的值，并且求出F(n)
                for (int j = 0; j < 9; j++) {
                    for (int k = 0; k < 9; k++) {
                        //  找出位置不同的元素，求出距离之和
                        if (Templist.list[j] == targetarray[k]) {
                            int step = 0;
                            if (k / 3 == j / 3)
                                step = Math.abs(j - k);
                            else if (k / 3 > j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            } else if (k / 3 < j / 3) {
                                step = Math.abs(k / 3 - j / 3) + Math.abs(j + (k / 3 - j / 3) * 3 - k);
                            }
                                Templist.Fresult =Templist.Fresult+ Math.abs(step) + count;
                                Templist.Hresult = Templist.Hresult+Math.abs(step);
                                break;
                        }
                    }
                }
                for(int k= 0;k<=resultlist.size()-1;k++){
                    if(Arrays.equals(Templist.list,resultlist.get(k).list))
                    {flag =1;break;}
                    else flag = 0;
                    //  将其存入treelist中
                }
                if(flag==0)treeList.add(Templist);
            }
            // 四个方法找到所有可能性后，进行比较，找到F(n)最小的一个方向,存入ResultList，在进行查找，直到ResultList最后一个参数的FResult=0
            int num = 1;
            int min = 0;
            while (num<treeList.size()) {
                //  找到最小的曼哈顿距离之和，将其存入resultlist中，之后清空treelist
                if (treeList.get(num).Hresult < treeList.get(min).Hresult)
                    min = num;
                num++;
            }
            //  赋值，清空
            resultlist.add(treeList.get(min));
            treeList.clear();

            if(resultlist.get(resultlist.size()-1).Hresult==0)break;
        }
        println("该八数码问题有解，最短步骤如下：");
        println("初始位置：");for(int w=0;w<9;w++){
            System.out.print(resultlist.get(0).list[w]);
        }
        println("");
        println("A*算法求得的最短变化过程：");
        for(int out = 1;out<=resultlist.size()-1;out++){
            for(int w = 0;w<9;w++){
                if(resultlist.get(out)!=null)
                System.out.print(resultlist.get(out).list[w]);
            }
            println("");
        }
    }
}
