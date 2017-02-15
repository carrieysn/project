package com.cifpay.lc.gateway.controller.batch;

import com.cifpay.lc.api.BusinessInput;
import com.cifpay.lc.api.BusinessOutput;
import com.cifpay.lc.api.gateway.batch.BatchInitLcService;
import com.cifpay.lc.api.gateway.batch.BatchOpenLcService;
import com.cifpay.lc.constant.BizConstants;
import com.cifpay.lc.constant.enums.AccountPropertyType;
import com.cifpay.lc.domain.batch.*;
import com.cifpay.lc.domain.security.MerchantRequest;
import com.cifpay.lc.gateway.common.exception.GatewayLcException;
import com.cifpay.lc.gateway.controller.GatewayBaseController;
import com.cifpay.lc.gateway.input.batch.BatchInitReq;
import com.cifpay.lc.gateway.input.batch.BatchOpenReq;
import com.cifpay.lc.gateway.mapper.batch.InitLcInputDtoMapper;
import com.cifpay.lc.gateway.mapper.batch.InitLcOutputBeanMapper;
import com.cifpay.lc.gateway.mapper.batch.OpenLcOutputBeanMapper;
import com.cifpay.lc.gateway.output.batch.BatchInitResp;
import com.cifpay.lc.gateway.output.batch.BatchOpenResp;
import com.cifpay.lc.gateway.output.batch.InitLcOutputDto;
import com.cifpay.lc.gateway.output.batch.OpenLcOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 批量开证
 *
 * @author sweet
 */
@RestController
@RequestMapping("/lc")
public class BatchOpenLcController extends GatewayBaseController {

    @Autowired
    private BatchInitLcService batchInitLcService;

    @Autowired
    private BatchOpenLcService batchOpenLcService;

    @ResponseBody
    @RequestMapping("/batchInit")
    public BatchInitResp init(@RequestBody MerchantRequest<BatchInitReq> merReq) {
        logger.info("===进入batch/init");
        BatchInitReq request = merReq.getData();

        BatchInitLcInputBean inputBean = new BatchInitLcInputBean();
        inputBean.setMerId(merReq.getMerId());
        inputBean.setReturnUrl(request.getReturnUrlBatch());
        inputBean.setNoticeUrl(request.getNoticeUrlBatch());

        inputBean.setPayerBankCode(request.getPayerBankCode());
        inputBean.setPayerBankAccountNo(request.getPayerBankAccountNo());
        if ("c".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.PERSONAL);
        } else if ("b".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.CORPORATE);
        }
        inputBean.setPayerMobile(request.getPayerMobile());
        inputBean.setRemark(request.getRemarkBatch());

        List<BatchInitLcInputDto> lcList = InitLcInputDtoMapper.ToBatchInitLcInputDtoList(request.getLcList());
        inputBean.setLcList(lcList);

        // 执行业务逻辑
        BusinessOutput<BatchInitLcOutputBean> output = batchInitLcService.execute(new BusinessInput<BatchInitLcInputBean>(inputBean));

        if (output.isSuccess() && output.getData() != null) {
            BatchInitLcOutputBean outputBean = output.getData();

            BatchInitResp response = new BatchInitResp();
            List<InitLcOutputDto> dtoList = InitLcOutputBeanMapper.ToInitLcOutputDto(outputBean.getLcList());
            response.setLcList(dtoList);
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }

    @ResponseBody
    @RequestMapping("/batchOpen")
    public BatchOpenResp open(@RequestBody MerchantRequest<BatchOpenReq> merReq) {
        logger.info("===进入batch/open");
        BatchOpenReq request = merReq.getData();

        BatchOpenLcInputBean inputBean = new BatchOpenLcInputBean();

        inputBean.setBatchOpenId(new Long(request.getBatchOpenId()));
        inputBean.setMerId(merReq.getMerId());
        inputBean.setRemark(inputBean.getRemark());

        // 执行业务逻辑
        BusinessOutput<BatchOpenLcOutputBean> output = batchOpenLcService.execute(new BusinessInput<BatchOpenLcInputBean>(inputBean));

        if (output.isSuccess() && output.getData() != null) {
            BatchOpenLcOutputBean outputBean = output.getData();

            BatchOpenResp response = new BatchOpenResp();
            response.setBatchOpenId(outputBean.getBatchOpenId().toString());
            response.setLcTotalAmount(outputBean.getLcTotalAmount().toString());

            List<OpenLcOutputDto> dtoList = OpenLcOutputBeanMapper.ToOpenLcOutputDto(outputBean.getLcList());
            response.setLcList(dtoList);
            return response;
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }

    @ResponseBody
    @RequestMapping("/batchInitAndOpen")
    public BatchOpenResp initAndOpen(@RequestBody MerchantRequest<BatchInitReq> merReq) {
        logger.info("===进入batch/initAndOpen");
        BatchInitReq request = merReq.getData();

        BatchInitLcInputBean inputBean = new BatchInitLcInputBean();
        inputBean.setMerId(merReq.getMerId());
        inputBean.setReturnUrl(request.getReturnUrlBatch());

        inputBean.setPayerBankCode(request.getPayerBankCode());
        inputBean.setPayerBankAccountNo(request.getPayerBankAccountNo());
        if ("c".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.PERSONAL);
        } else if ("b".equalsIgnoreCase(request.getPayerAccountType())) {
            inputBean.setPayerAccountType(AccountPropertyType.CORPORATE);
        }
        inputBean.setRemark(request.getRemarkBatch());

        List<BatchInitLcInputDto> lcList = InitLcInputDtoMapper.ToBatchInitLcInputDtoList(request.getLcList());
        inputBean.setLcList(lcList);

        // 请求INIT
        BusinessOutput<BatchInitLcOutputBean> output = batchInitLcService.execute(new BusinessInput<BatchInitLcInputBean>(inputBean));

        if (output.isSuccess() && output.getData() != null) {
            BatchInitLcOutputBean outputBean = output.getData();

            // 请求OPEN
            BatchOpenLcInputBean openInputBean = new BatchOpenLcInputBean();

            openInputBean.setBatchOpenId(outputBean.getBatchOpenId());
            openInputBean.setMerId(merReq.getMerId());
            openInputBean.setRemark(inputBean.getRemark());

            // 执行业务逻辑
            BusinessOutput<BatchOpenLcOutputBean> openOutput = batchOpenLcService.execute(new BusinessInput<BatchOpenLcInputBean>(openInputBean));

            if (openOutput.isSuccess() && openOutput.getData() != null) {
                BatchOpenLcOutputBean openOutputBean = openOutput.getData();

                BatchOpenResp response = new BatchOpenResp();
                response.setBatchOpenId(openOutputBean.getBatchOpenId().toString());
                response.setLcTotalAmount(BizConstants.decimalFormat.format(openOutputBean.getLcTotalAmount()));

                List<OpenLcOutputDto> dtoList = OpenLcOutputBeanMapper.ToOpenLcOutputDto(openOutputBean.getLcList());
                response.setLcList(dtoList);
                return response;
            }

            throw new GatewayLcException(openOutput.getReturnCode(), openOutput.getReturnMsg());
        }

        throw new GatewayLcException(output.getReturnCode(), output.getReturnMsg());
    }
}
