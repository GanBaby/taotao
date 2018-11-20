package com.taotao.portal.pojo;

import com.taotao.pojo.TbItem;

/**
 * @Author: 甘银道
 * @Description: java类作用描述
 * @Date: 2018-11-20 下午 21:52
 */
public class ItemInfo extends TbItem {


    public String[] getImages(){
        String image = getImage();
        if(image != null){
            String[] images = image.split(",");
            return images;
        }
        return null;
    }

}
