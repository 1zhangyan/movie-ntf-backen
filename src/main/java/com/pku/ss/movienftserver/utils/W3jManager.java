package com.pku.ss.movienftserver.utils;

import com.google.common.base.Strings;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description: TODO
 * @Author: xiaoche
 * @Date: 2021/9/8 14:01
 */
public class W3jManager {

    public static Web3j initWeb3j(String web3jUrl) throws Exception {
        Web3j web3 = Web3j.build(new HttpService(web3jUrl));
        return web3;
    }

    public static String loadWeb3jClientVersion(String web3jUrl) {
        try {
            Web3j web3j = initWeb3j(web3jUrl);
            Web3ClientVersion web3ClientVersion = web3j.web3ClientVersion().send();
            String clientVersion = web3ClientVersion.getWeb3ClientVersion();
            return clientVersion;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List<EthLog.LogResult> loadWeb3jEthLogs(String web3jUrl, String contractAddress, String... optionalTopics) {
        try {
            //建立私链连接
            Web3j web3j = initWeb3j(web3jUrl);
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


    public static String loadWeb3jEthCall(String web3jUrl,String to, String from, String data) {
        try {
            //建立私链连接
            Web3j web3j = initWeb3j(web3jUrl);
            org.web3j.protocol.core.methods.request.Transaction transaction = org.web3j.protocol.core.methods.request.Transaction.createEthCallTransaction(from, to, data);
            EthCall ethCall = web3j.ethCall(transaction,
                    DefaultBlockParameter.valueOf("latest")).send();
            return ethCall.getValue();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Transaction loadWeb3jTransactionByHash(String web3jUrl,String transactionHash) {
        try {
            //建立私链连接
            Web3j web3j = initWeb3j(web3jUrl);
            org.web3j.protocol.core.methods.response.Transaction transaction = web3j.ethGetTransactionByHash(transactionHash).send().getTransaction().orElse(null);
            return transaction;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //0失败 1成功
    public static String loadWeb3jTransactionStatus(String web3jUrl,String transactionHash) {
        try {
            String status = "0";
            Web3j web3j = initWeb3j(web3jUrl);
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
