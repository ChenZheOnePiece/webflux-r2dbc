package com.taiji.webfluxcurd.utils;

import cn.hutool.core.util.StrUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.CriteriaDefinition;
import org.springframework.data.relational.core.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public   static Query getQuery(PageDTO pageDTO) {
        int page = pageDTO.getPage()-1;
        int pageSize = pageDTO.getPageSize();
        List<SortDTO> sorts = pageDTO.getSorts();
        Query empty = Query.empty().with(PageRequest.of(page, pageSize));

        ArrayList<Sort.Order> objects = new ArrayList<>();
        for (SortDTO sort : sorts) {
            String direction = sort.getDirection();
            if (StrUtil.isNotBlank(direction) && "ASC".equals(direction.toUpperCase())) {
                objects.add(Sort.Order.asc(sort.getFieldName()));
            }
            if (StrUtil.isNotBlank(direction) && "DES".equals(direction.toUpperCase())) {
                objects.add(Sort.Order.desc(sort.getFieldName()));
            }
        }
        if (sorts.size() > 0) {
            empty=  empty.sort(Sort.by(objects));
        }
        Map<String,Object> filters = pageDTO.getFilters();
        List<Criteria> criterias = new ArrayList<>();
        filters.forEach((k,v) ->{
            Criteria criteria = Criteria.where(k).like("%"+v+"%");
            criterias.add(criteria);
        });
        if (criterias.size() > 0) {
            CriteriaDefinition from = CriteriaDefinition.from(criterias);
            empty= empty.query(from);
        }

        return empty;
    }
}
