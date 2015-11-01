package com.lite.face.framwork.bean.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 21:32.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class BuildAsthmaType {

    //一级标题
    private final String[] PRIMARYTITLES = new String[]{"有急性发作(多选)", "峰值仪(单选)", "呼吸问题(多选)", "夜间症状(单选)", "睡眠(单选)", "大便(单选)", "哮喘用药(单选)",
            "额外哮喘用药(单选)", "吸烟或二手烟(单选)", "辛辣刺激食品(单选)", "海鲜(单选)", "易过敏食品(单选)", "宠物(单选)", "特殊药(单选)", "散步慢走(单选)", "呼吸操(单选)", "自我感觉(单选)"};
    //二级标题
    //行动受限




    //夜间症状
    private final String[] SECONDARYSYMPTOM = new String[]{"无", "憋醒"};
    //睡眠
    private final String[] SECONDARYSLEEP = new String[]{">8小时", "6~8小时", "4~6小时", "<4小时"};
    //大便
    private final String[] SECONDARYSHIT = new String[]{"正常", "便秘", "腹泻"};
    //该用药
    private final String[] SECONDARYTHEDRUGUSE = new String[]{"完成", "完成部分", "完全未完成"};
    //多用药
    private final String[] SECONDARYMULTIDRUGUSE = new String[]{"无", "有"};
    //吸烟或二手烟
    private final String[] SECONDARYSMOOKING = new String[]{"无", "0~10支", "10~20支", "20~30支", "30+支"};
    //辛辣刺激食品
    private final String[] SECONDARYSPICYFOOD = new String[]{"无", "有"};
    //海鲜
    private final String[] SECONDARYSEAFOOD = new String[]{"无", "有"};
    //易过敏食品
    private final String[] SECONDARYALLERGYFOOD = new String[]{"无", "有"};
    //宠物
    private final String[] SECONDARYPET = new String[]{"无", "有"};
    //特殊药
    private final String[] SECONDARYDRUGS = new String[]{"无", "有"};
    //散步慢走
    private final String[] SECONDARYWALK = new String[]{"0~5000", "5000~10000", "10000~15000"};
    //呼吸操
    private final String[] SECONDARYBREAKINGEXERCISES = new String[]{"1次", "2次", "3次+"};
    //自我感觉
    private final String[] SECONDARYSELFFEELING = new String[]{"很好", "一般", "不好"};

    //三级标题
    private String[] SUBSPICYFOOD = new String[]{"辣椒", "花椒", "其它"};
    private String[] SUBSEAFOOD = new String[]{"虾", "蟹", "鱼", "其它"};
    private String[] SUBALLERGYFOOD = new String[]{"鸡蛋", "豆类", "牛奶", "其它"};
    private String[] SUBPETS = new String[]{"猫", "狗", "其它"};
    private String[] SUBDRUGS = new String[]{"抗生素", "阿司匹林", "其它"};

    private void initAsthmaData() {
        AsthmaType asthmaType = new AsthmaType();
        asthmaType.mPrimaryTypes = new ArrayList<>();
        PrimaryType primaryType = new PrimaryType();
        primaryType.mTitle = "有急性发作(多选)";
        primaryType.mSelected = true;
        primaryType.mSecondTypes = null;


    }

    //行动受限
    private SecondaryType build00() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无");
        subTypes.add(subType);
        subType = new SubType("步行或上楼气短(轻度)");
        subTypes.add(subType);
        subType = new SubType("稍事活动气短，讲话常有中继（中度）");
        subTypes.add(subType);
        subType = new SubType("休息时感气短，端坐呼吸，只能发单字表达（重度）");
        subTypes.add(subType);
        subType = new SubType("不能讲话，嗜睡或意识模糊（危重）");
        subTypes.add(subType);

        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "行动受限(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //情绪异常
    private SecondaryType build01() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无");
        subTypes.add(subType);
        subType = new SubType("有焦虑(轻度)");
        subTypes.add(subType);
        subType = new SubType("时有焦虑(中度)");
        subTypes.add(subType);
        subType = new SubType("常有焦虑和烦躁(重度)");
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "情绪异常(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //峰值仪
    private SecondaryType build10() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("未测量");
        subTypes.add(subType);
        subType = new SubType("PEF值大于预计值 或个人最侍值的80%");
        subTypes.add(subType);
        subType = new SubType("PEF值在预计值或个人最佳值的60%-80%之间");
        subTypes.add(subType);
        subType = new SubType("PEF值小于预计值或个人最佳值的60%");
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "峰值仪(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //呼吸问题
    private SecondaryType build20() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无");
        subTypes.add(subType);
        subType = new SubType("哮喘");
        subTypes.add(subType);
        subType = new SubType("咳嗽");
        subTypes.add(subType);
        subType = new SubType("胸闷");
        subTypes.add(subType);
        subType = new SubType("气急");
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = true;
        secondaryType.mTitle = "呼吸问题(多选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //呼吸问题
    private SecondaryType build30() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无");
        subTypes.add(subType);
        subType = new SubType("哮喘");
        subTypes.add(subType);
        subType = new SubType("咳嗽");
        subTypes.add(subType);
        subType = new SubType("胸闷");
        subTypes.add(subType);
        subType = new SubType("气急");
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = true;
        secondaryType.mTitle = "呼吸问题(多选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }
}
