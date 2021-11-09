package utils;

public class MyNumberFormat {

    public static Integer getChoice(String str){
        Integer num = null;
        try {
            num = Integer.parseInt(str);
        } catch (NumberFormatException e) {
            num = 0;
        }
        return num;
    }
}
