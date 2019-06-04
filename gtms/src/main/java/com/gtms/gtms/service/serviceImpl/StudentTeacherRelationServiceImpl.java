package com.gtms.gtms.service.serviceImpl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gtms.gtms.entity.NoPassThesis;
import com.gtms.gtms.entity.StudentTeacherRelation;
import com.gtms.gtms.mapper.NopassThesisMapper;
import com.gtms.gtms.mapper.StudentTeacherRelationMapper;
import com.gtms.gtms.service.StudentTeacherRelationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: 84644
 * @Date: 2019/4/10 9:14
 * @Description:
 **/
@Service
public class StudentTeacherRelationServiceImpl extends ServiceImpl<StudentTeacherRelationMapper, StudentTeacherRelation> implements StudentTeacherRelationService {
    @Autowired
    private StudentTeacherRelationMapper studentTeacherRelationMapper;

    @Autowired
    private NopassThesisMapper nopassThesisMapper;

    @Override
    public List<StudentTeacherRelation> getInfoByStudentNo(String studentNo) {
        return studentTeacherRelationMapper.getInfoByStudentNo(studentNo);
    }

    @Override
    public void deleteByThesisId(Integer[] ids) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ids.length; i++) {
            if (i == (ids.length - 1)) {
                sb.append(ids[i]);
            } else {
                sb.append(ids[i]).append(",");
            }
        }
        studentTeacherRelationMapper.deleteByThesisId(sb.toString());
    }

    @Override
    public Map<String, Object> getInfoByTeacherNo(int page, int rows, String teacherNo) {
        Map<String, Object> map = Maps.newHashMap();
        Page<StudentTeacherRelation> pageRecord = new Page<>(page, rows);
        List<StudentTeacherRelation> thesisInfoList = studentTeacherRelationMapper.getInfoByTeacherNo(teacherNo, pageRecord);
        thesisInfoList.forEach(item -> {
            item.setOpinionFlagStr(item.getOpinionFlag() == null ? "未审核" : (item.getOpinionFlag() == 1 ? "同意" : "未审核"));
            item.setTeacherOpinion(item.getTeacherOpinion() == null ? "" : item.getTeacherOpinion());
        });
        map.put("total", pageRecord.getTotal());
        map.put("rows", thesisInfoList);
        return map;
    }

    @Override
    public StudentTeacherRelation getInfoByThesisNo(String thesisNo) {
        return studentTeacherRelationMapper.getInfoByThesisNo(thesisNo);
    }

    @Override
    public void updateByThesisNo(String thesisNo, String opinion, String teacherOpinion) {
        studentTeacherRelationMapper.updateByThesisNo(thesisNo, opinion, teacherOpinion);
    }

    @Override
    public List<StudentTeacherRelation> getInfoByThesisNos(Integer[] ids) {
        return studentTeacherRelationMapper.getInfoByThesisNos(ids);
    }

    @Override
    public Map<String, Object> getAgreeThesisByTeacherNo(int page, int rows, String teacherNo) {
        Map<String, Object> map = Maps.newHashMap();
        Page<StudentTeacherRelation> pageRecord = new Page<>(page, rows);
        List<StudentTeacherRelation> thesisInfoList = studentTeacherRelationMapper.getAgreeThesisByTeacherNo(teacherNo, pageRecord);
        map.put("total", pageRecord.getTotal());
        map.put("rows", thesisInfoList);
        return map;
    }

    @Override
    public Map<String, Object> getAgreeThesisByStudentNo(int page, int rows, String studentNo) {
        Map<String, Object> map = Maps.newHashMap();
        Page<StudentTeacherRelation> pageRecord = new Page<>(page, rows);
        List<StudentTeacherRelation> thesisInfoList = studentTeacherRelationMapper.getAgreeThesisByStudentNo(studentNo, pageRecord);
        map.put("total", pageRecord.getTotal());
        map.put("rows", thesisInfoList);
        return map;
    }

    @Override
    public void updateTaskUrlByThesisNo(String taskUrl, String thesisNo) {
        studentTeacherRelationMapper.updateTaskUrlByThesisNo(taskUrl, thesisNo);
    }

    @Override
    public void updateThesisUrlByThesisNo(String thesisUrl, String thesisNo) {
        studentTeacherRelationMapper.updateThesisUrlByThesisNo(thesisUrl, thesisNo);
    }

    @Override
    public Map<String, Object> getThesisInfoDetail(StudentTeacherRelation studentTeacherRelation, int page, int rows) {
        // opinionFlagStr 1 未审核 2 未选题 3 审核通过 4 审核未通过
        Map<String, Object> map = Maps.newHashMap();
        List<StudentTeacherRelation> thesisInfoList = studentTeacherRelationMapper.getThesisInfoDetail(studentTeacherRelation);
        List<StudentTeacherRelation> noPassThesisList = nopassThesisMapper.selectAll(studentTeacherRelation);
        for (StudentTeacherRelation noPassThesis : noPassThesisList) {
            noPassThesis.setOpinionFlag(2);
            thesisInfoList.add(noPassThesis);
        }

        // 根据opinionFlagStr过滤数据
        List<StudentTeacherRelation> filterList = Lists.newArrayList();
        String flag = studentTeacherRelation.getOpinionFlagStr();
        if (!StringUtils.isEmpty(flag)) {
            if ("1".equals(flag)) {
                for (int i = 0; i < thesisInfoList.size(); i++) {
                    if (!StringUtils.isEmpty(thesisInfoList.get(i).getThesisNo()) && null == thesisInfoList.get(i).getOpinionFlag()) {
                        filterList.add(thesisInfoList.get(i));
                    } else {
                        continue;
                    }
                }
            } else if ("2".equals(flag)) {
                for (int i = 0; i < thesisInfoList.size(); i++) {
                    if (StringUtils.isEmpty(thesisInfoList.get(i).getThesisNo()) && null == thesisInfoList.get(i).getOpinionFlag()) {
                        filterList.add(thesisInfoList.get(i));
                    } else {
                        continue;
                    }
                }
                //thesisInfoList.stream().filter(item -> (StringUtils.isEmpty(item.getThesisNo()) && null == item.getOpinionFlag())).collect(Collectors.toList());
            } else if ("3".equals(flag)) {
                for (int i = 0; i < thesisInfoList.size(); i++) {
                    if ((!StringUtils.isEmpty(thesisInfoList.get(i).getThesisNo()) && thesisInfoList.get(i).getOpinionFlag() != null && 1 == thesisInfoList.get(i).getOpinionFlag())) {
                        filterList.add(thesisInfoList.get(i));
                    } else {
                        continue;
                    }
                }
            } else {
                for (int i = 0; i < thesisInfoList.size(); i++) {
                    if ((!StringUtils.isEmpty(thesisInfoList.get(i).getThesisNo()) && thesisInfoList.get(i).getOpinionFlag() != null && 2 == thesisInfoList.get(i).getOpinionFlag())) {
                        filterList.add(thesisInfoList.get(i));
                    } else {
                        continue;
                    }
                }
            }
        } else {
            filterList = thesisInfoList;
        }
        // list假分页
        int totalcount = filterList.size();
        int pagecount = 0;
        int m = totalcount % rows;
        if (m > 0) {
            pagecount = totalcount / rows + 1;
        } else {
            pagecount = totalcount / rows;
        }
        List<StudentTeacherRelation> subList = Lists.newArrayList();

        if (m == 0) {
            if (filterList.size() != 0 && filterList != null) {
                subList = filterList.subList((page - 1) * rows, rows * (page));
            }
        } else {
            if (page == pagecount) {
                subList = filterList.subList((page - 1) * rows, totalcount);
            } else {
                subList = filterList.subList((page - 1) * rows, rows * (page));
            }
        }
        map.put("total", filterList.size());
        map.put("rows", subList);
        return map;
    }

    @Override
    public int selectPassNum() {
        return studentTeacherRelationMapper.selectPassNum();
    }

    @Override
    public int selectUnderPassNumAndPassNum() {
        return studentTeacherRelationMapper.selectUnderPassNumAndPassNum();
    }

    @Override
    public int selectNoPassNum() {
        return studentTeacherRelationMapper.selectNoPassNum();
    }

}
