package theone.medusa.pers.projectx.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by xiayong on 2016/1/1.
 */
public class RandomUtils {

    /**
     * 蓄水池抽样算法
     * @param objects
     * @param n
     * @param <T>
     * @return
     */
    public static<T> T[] randomSelectN(List<T> objects,int n){
        if(objects.size() < n){
            throw new IllegalArgumentException("array's length must be more than n");
        }
        Random random = new Random();
        Object[] result = new Object[n];
        for(int i=0,l = objects.size();i<l;i++){
            if(i<n){
                result[i] = objects.get(i);
            }else{
                int j = random.nextInt(i);
                if(j<n){
                    result[j]=objects.get(i);
                }
            }
        }
       return (T[]) result;
    }

    /**
     * 从size大小的数组中随机取出k个下标
     * @param size
     * @param k
     * @return
     */
    public static int[] randomSelectIndex(int size,int k){
        if(size < k){
            throw new IllegalArgumentException("size must be bigger than k!");
        }
        int[] index = new int[k];
        for(int i=0;i<k;i++){
            index[i] = i;
        }
        Random random = new Random();
        for(int i=k;i<size;i++){
            int j = random.nextInt(i);
            if(j<k){
                index[j] = i;
            }
        }
        return index;
    }


}
