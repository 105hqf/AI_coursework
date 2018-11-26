#include "stdio.h"
#include "stdlib.h"
#include "math.h"
/**
设计一个温控系统的模糊推理系统
人工智能ppt2中的温控系统
洗涤时间的论域 0-60
油污以及灰尘的论域 0-100
输入格式：油污 Oil（S：少   M：中等   L:大量）  污渍 Stain（S：少   M：中等   L:大量）
输出格式： 洗涤时间 time（VS，S，M，L，VL）
**/
//  定义油污以及污渍的全局变量
#define OIL 100.0
#define Stain 100.0

struct elem
{
   double oil;
   double stain;
   int result;	
};
//  三个隶属度函数，用来计算污渍的隶属度.1代表SD，2代表MD，3代表LD
double ruleMD(double stain){
	if(stain<0||stain>100) return 0.0;  //  当返回的参数不在论域中时，返回错误
	else {
		//  当传入的参数在0-100之间时，该处有两种情况
		double result;
		if(stain>=0&&stain<=50)
			//  计算MD的结果，并且和同参数下的SD结果相比较，得出一个结果
			return stain/50.0;
		else {
			//  同上的操作，得出结果和同参数下的LD相比较
			return (100-stain)/50;
		}
	}
}
double ruleSD(double stain){
	//  SD部分的rule
	//  当输入的参数0<=x<=50,执行该方法
	double result = (50-stain)/50.0;
	double returnMDresult = ruleMD(stain);
	// 传参数到MD中，计算，并比较
	//  1、相同，则返回结果为SD，2、SD的结果大，则返回SD，3、MD的结果大，则返回MD的返回值
	if(result<returnMDresult)
		return 2.0;
	else return 1.0;
}
double ruleLD(double stain ){
	//  LD部分的rula
	//  当输入的参数在50-100之间时，执行

	//  同时将参数传入给MD，同时比较MD方法传回来的参数和该方法求出的值相比较，求出最后的最适合的预测值
	double returnMDresult = ruleMD(stain);
	double result = (stain - 50) / 50.0;
	//  比较后，得到预测值
	if(result<returnMDresult)
		return 2.0;
	else return 3.0;
}
double ruleMG(double oil){
    //  当传入的参数在0-100之间时，该处有两种情况
    if(oil<0||oil>100) return 0;  //  当在论域之外时，直接返回无结果
    else {
	    double result;
		if(oil>=0&&oil<=50)
			//  计算MD的结果，并且和同参数下的SD结果相比较，得出一个结果
			return oil/50.0;
		else {
			//  同上的操作，得出结果和同参数下的LD相比较
			return (100-oil)/50;
		}
    }
}
//  三个隶属度函数，用来计算油污的隶属度，1代表SG，2代表MG，3代表LG
double ruleSG(double oil){
	if(oil<0||oil>50)return 0.0;
	else {
		//  SG部分的rule
		//  当输入的参数0<=x<=50,执行该方法
		double result = (50-oil)/50.0;
		double returnMGresult = ruleMG(oil);
		// 传参数到MD中，计算，并比较
		//  1、相同，则返回结果为SD，2、SD的结果大，则返回SD，3、MD的结果大，则返回MD的返回值
		if(result<returnMGresult)
			return 2.0;
		else return 1.0;
	}
}
double ruleLG(double oil){
	//  LD部分的rula
	//  当输入的参数在50-100之间时，执行
	//  同时将参数传入给MG，同时比较MG方法传回来的参数和该方法求出的值相比较，求出最后的最适合的预测值
	double returnMGresult = ruleMG(oil);
	double result = (oil - 50) / 50.0;
	//  比较后，得到预测值
	if(result<returnMGresult)
		return 2.0;
	else return 3.0;
}
//  F函数，总的函数，从该函数中分流到rule的三个函数中
int Function(double oil,double stain){
	/** VS:  SD,SG
        S:   MD,SG
        M:   SD,MG   MD,MG   LD,SG
        L:   SD,LG   MD，LG  LD，MG
        XL:  LD,LG
	**/
	//  根据规则输出最后的洗涤时间
	double result_D,result_G;
	//  需要客户的正确输入
	if(oil<0||oil>OIL||stain<0||stain>Stain) return 0.0;
	else {
		//  根据参数的大小，分别传送给各个rula
		if(oil>=0&&oil<=50) result_G = ruleSG(oil);
		else result_G = ruleLG(oil);

		if(stain>=0&&stain<=50) result_D = ruleSD(stain);
		else result_D = ruleLD(stain);


		//  比较最后的结果
		if(result_D==1.0&&result_G==1.0) return 1;    //  return VS
		else if(result_G==1.0&&result_D==2.0) return 2;     //  return S
		else if((result_D==1.0&&result_G==2.0)||(result_G==2.0&&result_D==2.0)||(result_G==1.0&&result_D==3.0)) return 3;   //  return M
		else if((result_D==1.0&&result_G == 3.0)||(result_D == 2.0&&result_G==3.0)||(result_D==3.0&&result_G==2.0)) return 4;
		else if(result_G==3.0&&result_D==3.0) return 5;
	}
}
int main(int argc, char const *argv[])
{
	/* code */
	elem element[10]={{20.0,20.0,0},{20.0,30.0,0},{25.0,25.0,0},{30.0,30.0,0},{30.0,20.0,0},{65.0,77.0,0},{20.0,80.0,0},{80.0,80.0,0},{49.0,30.0,0},{50.0,50.0,0}};
	//  输入参数oil，stain
	for(int i=0;i<10;i++){
		element[i].result = Function(element[i].oil,element[i].stain);
		printf("油污：%.1lf  污渍：%.1lf  要进行的洗涤时间为：", element[i].oil,element[i].stain);
		switch(element[i].result){
			case 1:printf("VS\n");break;
			case 2:printf("S\n");break;
			case 3:printf("M\n");break;
			case 4:printf("L\n");break;
			case 5:printf("VL\n");break;
		}
	}
	return 0;
}