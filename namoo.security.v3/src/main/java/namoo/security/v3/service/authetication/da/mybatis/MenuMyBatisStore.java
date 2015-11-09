/*******************************************************************************
 * Copyright(c) 2015-2020 Incheon International Airport Corporation. 
 * All rights reserved. This software is the proprietary information of 
 * Incheon International Airport Corporation.
 *******************************************************************************/
package namoo.security.v3.service.authetication.da.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import namoo.security.v3.domain.authentication.entity.PMSMenu;
import namoo.security.v3.domain.authentication.store.MenuStore;
import namoo.security.v3.service.authetication.da.mybatis.mapper.MenuMapper;

/**
 * Authorization Store MyBatis Implementation
 *
 * @author <a href="mailto:eykim@nextree.co.kr">Kim, Eunyoung</a>
 * @since 2015. 11. 4
 */
@Repository
public class MenuMyBatisStore implements MenuStore {

    @Autowired
    private MenuMapper menuMapper;
    
    @Override
    public List<PMSMenu> readMenuListByRole(String roleId) {
        // 
        return menuMapper.selectMenuScreenListByRole(roleId);
    }

}

