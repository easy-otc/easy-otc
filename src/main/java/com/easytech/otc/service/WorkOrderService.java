package com.easytech.otc.service;

import org.springframework.stereotype.Service;

import com.easytech.otc.mapper.model.WorkOrder;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

@Service
public class WorkOrderService extends BaseService<WorkOrder> {


    /**
     * 创建一条新工单
     * @param workOrder
     */
    public void createNewWorkOrder(WorkOrder workOrder){
        if(workOrder == null){
            throw new BizException("工单信息为空");
        }
        Assert.hasText(workOrder.getTheme(),"工单主题不可为空");
//        Assert.hasText(workOrder.getUid(),"所属用户不可为空");
        Assert.hasText(workOrder.getContent(),"内容不可为空");
//        Assert.hasText( workOrder.getWorkOrderType(),"工单类型不可为空");
        workOrder.setStatus(0);         //新工单
        saveOrUpdate(workOrder);
    }

    /**
     * 更新工单信息
     * @param workOrder
     */
    public void updateWorkOrder(WorkOrder workOrder){
        if(workOrder == null){
            throw new BizException("工单信息为空");
        }
        Assert.hasText(workOrder.getTheme(),"工单主题不可为空");
//        Assert.hasText(workOrder.getUid(),"所属用户不可为空");
        Assert.hasText(workOrder.getContent(),"内容不可为空");
//        Assert.hasText( workOrder.getWorkOrderType(),"工单类型不可为空");
        saveOrUpdate(workOrder);
    }

    /**
     * 关闭工单
     * @param id
     */
    public void closeWorkOrder(Integer id){
        if(id == null){
            throw new BizException("工单号不存在");
        }
        WorkOrder workOrder = getById(id);
        if(workOrder != null){
            workOrder.setUpdateTime(new Date());
            workOrder.setStatus(3);
        }
        saveOrUpdate(workOrder);
    }

    /**
     * 根据uid 查询用户工单信息
     * @param uid
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<WorkOrder> findWorkOrderByUId(Integer uid,Integer pageNum,Integer pageSize){
        if(uid == null){
            return null;
        }
        pageNum = pageNum == null?0:pageNum;
        pageSize = pageSize == null?10:pageSize;
        WorkOrder workOrder = new WorkOrder();
        workOrder.setUid(uid);
        PageInfo<WorkOrder> workOrderList = queryPage(workOrder,pageNum,pageSize);
        return workOrderList;
    }

    /**
     * 根据workOrder 条件查询工单信息
     * @param workOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<WorkOrder> findWorkOrderByWorkOrder(WorkOrder workOrder,Integer pageNum,Integer pageSize){
        if(workOrder == null){
            return null;
        }
        pageNum = pageNum == null?0:pageNum;
        pageSize = pageSize == null?10:pageSize;
        PageInfo<WorkOrder> workOrderList = queryPage(workOrder,pageNum,pageSize);
        return workOrderList;
    }

    public void saveOrUpdate(WorkOrder workOrder){
        if(workOrder == null){
            return;
        }
        if(workOrder.getId() == null){
            workOrder.setCreateTime(new Date());
            workOrder.setUpdateTime(new Date());
            insert(workOrder);
        }else{
            workOrder.setUpdateTime(new Date());
            updateById(workOrder);
        }
    }

    public String workOrderSendMsg(Integer id,String msg,String time){
        Assert.hasText(msg,"工单消息不可为空");
        Assert.hasText(time,"工单消息时间不可为空");
        WorkOrder workOrder = getById(id);
        String newMsg = null;
        if(workOrder != null){
            newMsg = appendMsg(workOrder.getCommunicateRecord(),time,msg,"");
            workOrder.setCommunicateRecord(newMsg);
        }
        return newMsg;
    }

    /**
     * @param oldMsg
     * @param time
     * @param msg
     * @param operator  用户在左，客服在右
     * @return
     */
    private String appendMsg(String oldMsg,String time,String msg,String operator){
        String HTML_WORK_MSG_END = "<div class=\"clear\"></div>";
        String HTML_WORK_MSG_TIME = "<div class=\"sm text-left pdlr10 message-box message-right message-right\"><div class=\"time\">$1</div><div class=\"clear\"></div>";
        String HTML_WORK_MSG_MSG = "<div class=\"message-content\">$2</div></div><div class=\"clear\"></div>";
        StringBuffer stringBuffer = null;
        if(StringUtils.isBlank(oldMsg)){
            stringBuffer = new StringBuffer();
        }else{
            stringBuffer = new StringBuffer(oldMsg);
            stringBuffer.substring(0,stringBuffer.indexOf(HTML_WORK_MSG_END));
        }
        stringBuffer.append(HTML_WORK_MSG_TIME.replace("$1",time));
        stringBuffer.append(HTML_WORK_MSG_MSG.replace("$2",msg));
        stringBuffer.append(HTML_WORK_MSG_END);
        return stringBuffer.toString();

    }

}