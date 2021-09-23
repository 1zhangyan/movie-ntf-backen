package com.pku.ss.movienftserver.utils;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthCall;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.Transaction;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.util.*;

/**
 * @Description: TODO
 * @Author: xiaoche
 * @Date: 2021/9/8 14:01
 */
@Component
@Slf4j
public class W3jManager {

    @Value("${qtumClient.url}")
    private String clientUrl;
    @Value("${qtumClient.username}")
    private String username;
    @Value("${qtumClient.password}")
    private String password;

    public Web3j initWeb3j() throws Exception {
        String cred = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        Map<String, String> headers = new HashMap<String, String>(1);
        headers.put("Authorization", "Basic " + cred);
        HttpService httpService = new HttpService(clientUrl);
        httpService.addHeaders(headers);
        Web3j web3 = Web3j.build(httpService);
        return web3;
    }

    public String loadWeb3jClientVersion() {
        try {
            Web3j web3j = initWeb3j();
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            return clientVersion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  List<EthLog.LogResult> loadWeb3jEthLogs( String contractAddress, String... optionalTopics) {
        try {
            //建立私链连接
            Web3j web3j = initWeb3j();
            List<String> address = new ArrayList<>();
            address.add(contractAddress);
            EthFilter ethFilter =
                    new EthFilter(
                            DefaultBlockParameterName.EARLIEST,
                            DefaultBlockParameterName.LATEST,
                            address
                    );
            ethFilter.addOptionalTopics(optionalTopics);
            EthLog ethLog = web3j.ethGetLogs(ethFilter).send();
            List<EthLog.LogResult> logs = ethLog.getLogs();
            return logs;
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public String loadWeb3jEthCall(String to, String from, String data) {
        try {
            //建立私链连接
            Web3j web3j = initWeb3j();
            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(from, to, data);
            EthCall ethCall = web3j.ethCall(transaction,
                    DefaultBlockParameter.valueOf("latest")).send();
            return ethCall.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public  Transaction loadWeb3jTransactionByHash(String transactionHash) {
        try {
            //建立私链连接
            Web3j web3j = initWeb3j();
            org.web3j.protocol.core.methods.response.Transaction transaction = web3j.ethGetTransactionByHash(transactionHash).send().getTransaction().orElse(null);
            return transaction;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //0失败 1成功
    public  String loadWeb3jTransactionStatus(String transactionHash) {
        try {
            String status = "0";
            Web3j web3j = initWeb3j();
            EthGetTransactionReceipt ethGetTransactionReceipt = web3j.ethGetTransactionReceipt(transactionHash).sendAsync().get();
            String status1 = ethGetTransactionReceipt.getTransactionReceipt().get().getStatus();
            if (!Strings.isNullOrEmpty(status1)) {
                status = status1.substring(2);
            }
            return status;
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

}
