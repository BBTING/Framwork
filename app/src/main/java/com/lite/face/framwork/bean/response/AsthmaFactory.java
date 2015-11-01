package com.lite.face.framwork.bean.response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by whoislcj on 2015/11/1 - 21:32.
 * Mail: handgunbreak@gmail.com
 * Copyright: 杭州医本健康科技有限公司(2014-2015)
 * Description:
 */
public class AsthmaFactory {

    //三级标题
    private String[] SUBSPICYFOOD = new String[]{"辣椒", "花椒", "其它"};
    private String[] SUBSEAFOOD = new String[]{"虾", "蟹", "鱼", "其它"};
    private String[] SUBALLERGYFOOD = new String[]{"鸡蛋", "豆类", "牛奶", "其它"};
    private String[] SUBPETS = new String[]{"猫", "狗", "其它"};
    private String[] SUBDRUGS = new String[]{"抗生素", "阿司匹林", "其它"};

    public  AsthmaType asthmaType;

    public void initAsthmaData() {
        asthmaType = new AsthmaType();
        asthmaType.mPrimaryTypes = new ArrayList<>();
        asthmaType.mPrimaryTypes.add(buildPrimaryType0());
        asthmaType.mPrimaryTypes.add(buildPrimaryType1());
        asthmaType.mPrimaryTypes.add(buildPrimaryType2());
        asthmaType.mPrimaryTypes.add(buildPrimaryType3());
        asthmaType.mPrimaryTypes.add(buildPrimaryType4());
        asthmaType.mPrimaryTypes.add(buildPrimaryType5());
        asthmaType.mPrimaryTypes.add(buildPrimaryType6());
        asthmaType.mPrimaryTypes.add(buildPrimaryType7());
        asthmaType.mPrimaryTypes.add(buildPrimaryType8());
        asthmaType.mPrimaryTypes.add(buildPrimaryType9());
        asthmaType.mPrimaryTypes.add(buildPrimaryType10());
        asthmaType.mPrimaryTypes.add(buildPrimaryType11());
        asthmaType.mPrimaryTypes.add(buildPrimaryType12());
        asthmaType.mPrimaryTypes.add(buildPrimaryType13());
        asthmaType.mPrimaryTypes.add(buildPrimaryType14());
        asthmaType.mPrimaryTypes.add(buildPrimaryType15());
        asthmaType.mPrimaryTypes.add(buildPrimaryType16());
    }

    //第一级数据初始化
    private PrimaryType buildPrimaryType0() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSelected = true;
        primaryType.mSecondTypes = new ArrayList<>(2);
        primaryType.mSecondTypes.add(build00());
        primaryType.mSecondTypes.add(build01());
        primaryType.mTitle = "有急性发作(多选)";
        return primaryType;
    }

    //行动受限
    private SecondaryType build00() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("步行或上楼气短(轻度)", -20);
        subTypes.add(subType);
        subType = new SubType("稍事活动气短，讲话常有中继（中度）", -30);
        subTypes.add(subType);
        subType = new SubType("休息时感气短，端坐呼吸，只能发单字表达（重度）", -40);
        subTypes.add(subType);
        subType = new SubType("不能讲话，嗜睡或意识模糊（危重）", -50);
        subTypes.add(subType);

        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;//subType只能单选
        secondaryType.mTitle = "行动受限(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }



    //情绪异常
    private SecondaryType build01() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有焦虑(轻度)", -20);
        subTypes.add(subType);
        subType = new SubType("时有焦虑(中度)", -30);
        subTypes.add(subType);
        subType = new SubType("常有焦虑和烦躁(重度)", -40);
        subTypes.add(subType);
        subType = new SubType("嗜睡或意识模糊(危重)", -50);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "情绪异常(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType1() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build10());
        primaryType.mTitle = "峰值仪(单选)";
        return primaryType;
    }

    //峰值仪
    private SecondaryType build10() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("未测量", 0);
        subTypes.add(subType);
        subType = new SubType("PEF值大于预计值 或个人最佳值的80%", 0);
        subTypes.add(subType);
        subType = new SubType("PEF值在预计值或个人最佳值的60%-80%之间", -30);
        subTypes.add(subType);
        subType = new SubType("PEF值小于预计值或个人最佳值的60%", -50);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "峰值仪(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType2() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build20());
        primaryType.mTitle = "呼吸问题(多选)";
        return primaryType;
    }

    //呼吸问题
    private SecondaryType build20() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("喘息", -15);
        subTypes.add(subType);
        subType = new SubType("咳嗽", -15);
        subTypes.add(subType);
        subType = new SubType("胸闷", -15);
        subTypes.add(subType);
        subType = new SubType("气急", -15);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = true;
        secondaryType.mTitle = "呼吸问题(多选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType3() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build30());
        primaryType.mTitle = "夜间症状(单选)";
        return primaryType;
    }

    //夜间症状
    private SecondaryType build30() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("憋醒", -15);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "夜间症状(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }


    //第二级数据初始化
    private PrimaryType buildPrimaryType4() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build40());
        primaryType.mTitle = "睡眠(单选)";
        return primaryType;
    }

    //睡眠(单选)
    private SecondaryType build40() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType(">8小时", 0);
        subTypes.add(subType);
        subType = new SubType("6~8小时", -5);
        subTypes.add(subType);
        subType = new SubType("4~6小时", -10);
        subTypes.add(subType);
        subType = new SubType("<4小时", -15);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "睡眠(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }


    //第二级数据初始化
    private PrimaryType buildPrimaryType5() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build40());
        primaryType.mTitle = "大便(单选)";
        return primaryType;
    }

    //大便(单选)
    private SecondaryType build50() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("正常", 0);
        subTypes.add(subType);
        subType = new SubType("便秘", -5);
        subTypes.add(subType);
        subType = new SubType("腹泻", -5);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "大便(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }


    //第二级数据初始化
    private PrimaryType buildPrimaryType6() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build60());
        primaryType.mTitle = "哮喘用药(单选)";
        return primaryType;
    }

    //
    private SecondaryType build60() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("完成", 0);
        subTypes.add(subType);
        subType = new SubType("完成部分", -10);
        subTypes.add(subType);
        subType = new SubType("完全未完成", -20);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "哮喘用药(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType7() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build70());
        primaryType.mTitle = "额外哮喘用药(单选)";
        return primaryType;
    }

    //
    private SecondaryType build70() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有", -10);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "额外哮喘用药(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType8() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build80());
        primaryType.mTitle = "吸烟或二手烟(单选)";
        return primaryType;
    }

    //
    private SecondaryType build80() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("0~10支", -5);
        subTypes.add(subType);
        subType = new SubType("10~20支", -15);
        subTypes.add(subType);
        subType = new SubType("20~30支", -20);
        subTypes.add(subType);
        subType = new SubType("30+支", -25);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "吸烟或二手烟(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType9() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build90());
        primaryType.mTitle = "辛辣刺激食品(单选)";
        return primaryType;
    }

    //
    private SecondaryType build90() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有", -5);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "辛辣刺激食品(单选)";
        secondaryType.mSubTypes = subTypes;
        subType.mInnerTypes = new ArrayList<>(3);
        subType.mInnerTypes.add(new InnerType("辣椒"));
        subType.mInnerTypes.add(new InnerType("花椒"));
        subType.mInnerTypes.add(new InnerType("其它"));
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType10() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build100());
        primaryType.mTitle = "海鲜(单选)";
        return primaryType;
    }

    //
    private SecondaryType build100() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有", -5);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "海鲜(单选)";
        secondaryType.mSubTypes = subTypes;
        subType.mInnerTypes = new ArrayList<>(4);
        subType.mInnerTypes.add(new InnerType("虾"));
        subType.mInnerTypes.add(new InnerType("蟹"));
        subType.mInnerTypes.add(new InnerType("鱼"));
        subType.mInnerTypes.add(new InnerType("其它"));
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType11() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build110());
        primaryType.mTitle = "易过敏食品(单选)";
        return primaryType;
    }

    //
    private SecondaryType build110() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有", -5);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "易过敏食品(单选)";
        secondaryType.mSubTypes = subTypes;
        subType.mInnerTypes = new ArrayList<>(4);
        subType.mInnerTypes.add(new InnerType("鸡蛋"));
        subType.mInnerTypes.add(new InnerType("豆类"));
        subType.mInnerTypes.add(new InnerType("牛奶"));
        subType.mInnerTypes.add(new InnerType("其它"));
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType12() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build120());
        primaryType.mTitle = "宠物(单选)";
        return primaryType;
    }

    //
    private SecondaryType build120() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有", -5);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "宠物(单选)";
        secondaryType.mSubTypes = subTypes;
        subType.mInnerTypes = new ArrayList<>(3);
        subType.mInnerTypes.add(new InnerType("猫"));
        subType.mInnerTypes.add(new InnerType("狗"));
        subType.mInnerTypes.add(new InnerType("其它"));
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType13() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build130());
        primaryType.mTitle = "特殊药(单选)";
        return primaryType;
    }

    //
    private SecondaryType build130() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("无", 0);
        subTypes.add(subType);
        subType = new SubType("有", -5);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "特殊药(单选)";
        secondaryType.mSubTypes = subTypes;
        subType.mInnerTypes = new ArrayList<>(3);
        subType.mInnerTypes.add(new InnerType("抗生素"));
        subType.mInnerTypes.add(new InnerType("阿司匹林"));
        subType.mInnerTypes.add(new InnerType("其它"));
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType14() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build140());
        primaryType.mTitle = "散步慢走(单选)";
        return primaryType;
    }

    //
    private SecondaryType build140() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("0~5000", 0);
        subTypes.add(subType);
        subType = new SubType("5000~10000", 5);
        subTypes.add(subType);
        subType = new SubType("10000~15000", 10);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "散步慢走(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType15() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build150());
        primaryType.mTitle = "呼吸操(单选)";
        return primaryType;
    }

    //
    private SecondaryType build150() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("1次", 0);
        subTypes.add(subType);
        subType = new SubType("2次", 5);
        subTypes.add(subType);
        subType = new SubType("3次", 10);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "呼吸操(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }

    //第二级数据初始化
    private PrimaryType buildPrimaryType16() {
        PrimaryType primaryType = new PrimaryType();
        primaryType.mSecondTypes = new ArrayList<>(1);
        primaryType.mSecondTypes.add(build160());
        primaryType.mTitle = "呼吸操(单选)";
        return primaryType;
    }

    //
    private SecondaryType build160() {
        List<SubType> subTypes = new ArrayList<>();
        SubType subType = new SubType("很好", 0);
        subTypes.add(subType);
        subType = new SubType("一般", -10);
        subTypes.add(subType);
        subType = new SubType("不好", -20);
        subTypes.add(subType);
        SecondaryType secondaryType = new SecondaryType();
        secondaryType.mMulti = false;
        secondaryType.mTitle = "自我感觉(单选)";
        secondaryType.mSubTypes = subTypes;
        return secondaryType;
    }
}
