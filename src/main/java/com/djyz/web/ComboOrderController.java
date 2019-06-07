package com.djyz.web;

import com.djyz.domain.ComboOrder;
import com.djyz.domain.ComboOrderState;
import com.djyz.service.ComboOrderService;
import com.djyz.util.AjaxRes;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Api(value = "/ComboOrder", tags = "ComboOrder接口")
public class ComboOrderController {
    @Autowired
    private ComboOrderService comboOrderService;

    /*获取全部订单*/
    @GetMapping("/getAllComboOrders")
    @ResponseBody
    public List<ComboOrder> getAllComboOrders(){
        return comboOrderService.getAllComboOrders();
    }

    /*添加订单-*/
    /**
     *参数：comOrderId（自动生成），price,
     * comOderDate(自动生成今天日期)，startDate（拍摄日期，点击拍摄地点进行选择（几号）），
     * combo.id,  customer.id,  shootingLocation.id, shootingState（默认为1）
     * */
    @PostMapping("/addComboOrders/{coId}/{custId}/{lid}/{price}/{startDate}")
    @ResponseBody
    public AjaxRes addComboOrders(@PathVariable Long coId,@PathVariable Long custId,@PathVariable Long lid,@PathVariable Double price,@PathVariable String startDate){
        return comboOrderService.addComboOrders(coId,custId,lid,price,startDate);
    }

    /*根据id查询订单*/
    @GetMapping("/getComboOrdersWithId/{comOrderId}")
    @ResponseBody
    public ComboOrder  getComboOrdersWithId(@PathVariable Long comOrderId){
        return comboOrderService.getComboOrdersWithId(comOrderId);
    }


    //获取全部订单状态
    @GetMapping("/getAllOrderStates")
    @ResponseBody
    public List<ComboOrderState> getAllOrderStates(){
        return comboOrderService.getAllOrderStates();
    }

    /*修改订单状态*/
    @PutMapping("/editOrderStateWithId/{comOrderId}/{osId}")
    @ResponseBody
    public AjaxRes editOrderStateWithId(@PathVariable Long comOrderId,@PathVariable Long osId){
        return comboOrderService.editOrderStateWithId(comOrderId,osId);
    }







}
