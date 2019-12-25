package com.mvvm.network;


import java.util.HashMap;

/**
 * @author :  lwb
 * Date: 2019/12/25
 * Desc:
 */
public interface INetworkRequestInfo {
    HashMap<String, String> getRequestHeaderMap();
    boolean isDebug();
}
