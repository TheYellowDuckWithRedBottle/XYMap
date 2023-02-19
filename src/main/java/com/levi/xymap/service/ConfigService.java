package com.levi.xymap.service;

import com.levi.xymap.entity.Configuration;
import com.levi.xymap.entity.ThematicMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public interface ConfigService {
    /**
     * 修改属性
     * @param propKey 属性名
     * @param propVal 属性值
     * @return 是否修改成功
     */
    boolean alterProperty(String propKey, String propVal);

    /**
     * 获取所有模板缩率图信息
     * @return
     */
    List<Map> getThumbTplInfos();

    /**
     * 获取所有的专题图
     * @return
     */
    List<ThematicMap> getThematicMaps();

    /**
     * 根据id删除专题图
     * @param id
     */
    void deleteThematicMap(String id);

    /**
     * 更新专题图
     * @param thematicMap
     * @return
     */
    ThematicMap updateThematicMap(ThematicMap thematicMap);

    /**
     * 根据模板名称获取配置
     * @param tplName 模板名称
     * @return Configuration 配置信息
     */
    Configuration getConfiguration(String tplName);

    /**
     * 创建根据模板名称模板
     * @param tplName
     * @return
     */
    Configuration createTpl(String tplName);

    Configuration createTpl(String tpl, String name,String description,String thematicMap,String parentTpl);

    /**
     * 根据模板名称删除模板
     * @param tplName
     */
    void deleteTpl(String tplName);

    /**
     * 根据模板id获取所有的服务
     * @param id 模板id
     * @return
     */
    List getAllServices(String id);

    /**
     *
     * @param mapId
     * @param layerName
     * @return
     */
    List<Map> getLayerFields(String mapId,String layerName);


}
