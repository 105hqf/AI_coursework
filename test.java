import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;

class matrix{int x;int y;}//  一个矩阵类
public class test {
    //  人工智能实验课实验二作业的主代码
    //  利用深度遍历解决卒子通阵的问题
    private static int[][] a ={
            {1,0,1,0},
            {0,0,0,0},
            {1,1,0,1},
            {0,1,0,0}
    };//  定义一个阵型，1代表有敌军镇守的区域，0代表安全区域
    public static void main(String arg[]){
        matrix[] Sqstack=new matrix[100];//  深度遍历栈
        int top =0,ctop=0;//  栈顶指针
        int [][] flag = new int[4][4];
        int succCount = 1;//  成功通过的路径数量
        matrix[] CoverStack = new matrix[100];//  转置栈，用于顺序输出深度遍历栈所获得的结果
        //  入栈
        for(int j=0;j<4;j++){
            if(a[0][j]==0){
                Sqstack[top] = new matrix();
                Sqstack[top].x=0;Sqstack[top].y=j;
                top++;
                flag[0][j]=1;
                //  标记该位置的flag参数
                //  入栈后直接进行接下来的查找操作
                //  首先找他的正下方的元素
                for(;;) {
                    if (top - 1 >= 0 && a[Sqstack[top - 1].x + 1][Sqstack[top-1].y] == 0 && flag[Sqstack[top - 1].x + 1][Sqstack[top - 1].y] != 1&&Sqstack[top-1].x+1<=3) {
                        //  如果正下方的参数是零，那么入栈
                        Sqstack[top] = new matrix();
                        Sqstack[top].x=Sqstack[top-1].x+1;
                        Sqstack[top].y = Sqstack[top-1].y;
                        flag[Sqstack[top].x][Sqstack[top].y] = 1;//  打上已被查阅的标记
                        top++;

                       }
                        //  如果不是零，查找两边的参数，并且是没有被访问过的参数
                        //  假设先看左边
                         else if (top - 1 >= 0 && Sqstack[top - 1].y - 1 >= 0 && flag[Sqstack[top - 1].x][Sqstack[top - 1].y - 1] == 0 && a[Sqstack[top - 1].x][Sqstack[top - 1].y - 1] != 1) {
                            //  当左边的参数不会越界且没有被访问过，则压入栈中
                            Sqstack[top] = new matrix();
                            Sqstack[top].x = Sqstack[top - 1].x;
                            Sqstack[top].y = Sqstack[top - 1].y - 1;
                            flag[Sqstack[top].x][Sqstack[top].y] = 1;//  打上已被查阅的标记
                            top++;
                        } else if (top - 1 >= 0 && Sqstack[top - 1].y + 1 <= 3 && flag[Sqstack[top - 1].x][Sqstack[top - 1].y + 1] != 1 && a[Sqstack[top - 1].x][Sqstack[top - 1].y + 1] != 1) {
                            //  当右边的参数不会越界且没有被访问，则压入栈
                            Sqstack[top] = new matrix();
                            Sqstack[top].x = Sqstack[top - 1].x;
                            Sqstack[top].y = Sqstack[top - 1].y + 1;
                            flag[Sqstack[top].x][Sqstack[top].y] = 1;//  打上已被查阅的标记
                            top++;
                        } else {
                            //  当左右两边的参数都不符合要查找的要求，则返回上一个节点进行查找，出栈顶指针

                            top = top - 1;
                            if(top==0)break;
                        }
                        if(top-1>=0&&Sqstack[top-1].x==3) {
                            //  当已经有参数显示到3时，表示已经到达最底层，则退出循环，输出这个栈，并表示查阅成功
                            while (top != 0) {
                                //  将深度遍历所获得结果栈转置成可以正常顺序输出
                                top = top - 1;
                                CoverStack[ctop] = new matrix();
                                CoverStack[ctop] = Sqstack[top];
                                ctop++;
                            }
                            println("输出第"+succCount+"条可通路径：");
                            while(ctop!=0){
                                //  顺序输出结果
                                ctop = ctop-1;
                                println("( "+CoverStack[ctop].x+", "+CoverStack[ctop].y+" )");
                                flag = new int[4][4];//  成功输出一条可通过的路径，则清空所有的flag标志。

                            }
                            succCount++;
                            break;
                    }
                }
            }
        }
        if(succCount==0)println("不存在可以通过的路径");
    }

}
