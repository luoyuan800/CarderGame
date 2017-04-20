package cn.gavin.card.utils;

/**
 * Created by gluo on 4/18/2017.
 */
public class IDParser {
    public static String getGroup(String id){
        int groupLength = Integer.parseInt(id.substring(0,1));
        int groupStart = 2;
        int groupEnd = groupStart + groupLength;
        return id.substring(groupStart, groupEnd);
    }

    public static String getProduce(String id){
        int groupLength = Integer.parseInt(id.substring(0,1));
        int produceStart = 2 + groupLength;
        return id.substring(produceStart, produceStart + 2);
    }

    public static String buildTypeId(String group, String produce, String type){
        return group.length() + type.length() + group + produce + type;
    }

    public static String getType(String id){
        int groupLength = Integer.parseInt(id.substring(0,1));
        int typeLength = Integer.parseInt(id.substring(1,2));
        int typeStart = 2 + groupLength + 2;
        int typeEnd = typeStart + typeLength;
        return id.substring(typeStart, typeEnd);
    }

    public static boolean isValidatedId(String id){
        int groupLength = Integer.parseInt(id.substring(0,1));
        int typeLength = Integer.parseInt(id.substring(1,2));
        int length = 2 + groupLength + 2 + typeLength + 3;
        return id.length() == length;
    }
}