import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collections;
public class Solution {
    public static void main(String[] args) {
        ArrayList<String> abc = Permutation("123");
        System.out.println(abc);

    }
    public static ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<String>();
        if (str == null || str.length() <= 0)
            return res;
        //结果去重
        HashSet<String> set = new HashSet<String>();
        dfs(set, str.toCharArray(), 0);
        res.addAll(set);
        Collections.sort(res);
        return res;
    }

    public static void dfs(HashSet<String> set, char[] str, int k) {
        //得到结果
        if (k == str.length) {
            set.add(new String(str));
            return;
        }
        for (int i = 0; i < str.length; i++) {
            swap(i, k, str);
            dfs(set, str, k + 1);
            //回溯
            swap(i, k, str);
        }
    }

    public static void swap(int i, int j, char[] str){
        if(i != j){
            char temp = str[i];
            str[i] = str[j];
            str[j] = temp;
        }
    }
}