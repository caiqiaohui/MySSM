package com.swjd.test;

public class StringDemo {
    public static void main(String[] args) {
        StringBuffer  myStr=new StringBuffer("hello world!");
        String result=myStr.substring(0,myStr.indexOf("!"));
        System.out.println(result);

        StringDemo stringDemo=new StringDemo();

        stringDemo.mytest(new Student(){
            @Override
            public void tiaopi() {
               System.out.println("cai qiao hui ！");
            }
        });

    }
    public   void mytest(Student  student){
        student.tiaopi();
    }
}

class   Student{
    public  void tiaopi(){
        System.out.println("小蔡");
    }
}
