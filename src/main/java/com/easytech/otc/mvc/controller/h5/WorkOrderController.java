package com.easytech.otc.mvc.controller.h5;

import com.easytech.otc.mapper.model.WorkOrder;
import com.easytech.otc.mvc.controller.WebConst;
import com.easytech.otc.mvc.protocol.ACL;
import com.easytech.otc.mvc.protocol.Resp;
import com.easytech.otc.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by joel on 2018/7/24.
 */
@RestController
@RequestMapping(WebConst.H5_V1_PREFIX + "/workOrder/")
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;
    @PostMapping("getWorkOrderByUid")
    @ACL(authControl = false)
    public Resp< PageInfo<WorkOrder>> getWorkOrderByUid(@PathVariable Integer Uid, Integer pageNum, Integer pageSize){
//        Assert.hasText(Uid,"用户编号不可为空");

        PageInfo<WorkOrder> pageInfo = workOrderService.findWorkOrderByUId(Uid,pageNum,pageSize);
        return  Resp.newSuccessResult(pageInfo);
    }

    @PostMapping("getWorkOrderByWorkId")
    @ACL(authControl = false)
    public Resp<WorkOrder> getWorkOrderByWorkId(@PathVariable Integer id){
//        Assert.hasText(id,"工单id不可为空");
        WorkOrder workOrder = workOrderService.getById(id);
        return Resp.newSuccessResult(workOrder);
    }

    @PostMapping("getWorkOrderCommunicateRecord")
    @ACL(authControl = false)
    public Resp<String> getWorkOrderCommunicateRecordByid(@PathVariable Integer id){
//        Assert.hasText(id,"工单id不可为空");
        WorkOrder workOrder = workOrderService.getById(id);
        return Resp.newSuccessResult(workOrder.getCommunicateRecord());
    }

    /**
     * 工单发送消息
     * @param id
     * @param msg
     * @param time
     * @return  当前最新消息
     */
    @PostMapping("sendMsg")
    @ACL(authControl = false)
    public Resp<String> workOrderSendMsg(@PathVariable Integer id,@PathVariable String msg,@PathVariable String time){
        String newMsg = workOrderService.workOrderSendMsg(id,time,msg);
        return Resp.newSuccessResult(newMsg);
    }

}
