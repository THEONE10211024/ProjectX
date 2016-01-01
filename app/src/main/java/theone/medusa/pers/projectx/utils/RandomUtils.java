package theone.medusa.pers.projectx.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xiayong on 2016/1/1.
 */
public class RandomUtils {
    public static<T> List<T> randomSelectN(T[] objects,int n){
        if(objects.length < n){
            throw new IllegalArgumentException("array's length must be more than n");
        }
        Random random = new Random();
        List<T> result = new ArrayList<>();
        int length = objects.length;
        for(int i = 0;i < n;i++){
            int j = random.nextInt(length-i)+i;
            T temp = objects[i];
            objects[i] = objects[j];
            objects[j] = temp;
            result.add(objects[i]);
        }
        return result;
    }
}
