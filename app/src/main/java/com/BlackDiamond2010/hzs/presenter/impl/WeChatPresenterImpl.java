package com.BlackDiamond2010.hzs.presenter.impl;

import com.BlackDiamond2010.hzs.app.AppConstants;
import com.BlackDiamond2010.hzs.bean.wechat.WXHttpResponse;
import com.BlackDiamond2010.hzs.bean.wechat.WXItemBean;
import com.BlackDiamond2010.hzs.http.service.WeChatService;
import com.BlackDiamond2010.hzs.http.utils.Callback;
import com.BlackDiamond2010.hzs.presenter.BasePresenter;
import com.BlackDiamond2010.hzs.presenter.WeChatPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by quantan.liu on 2017/3/30.
 */

public class WeChatPresenterImpl extends BasePresenter<WeChatPresenter.View> implements WeChatPresenter.Presenter {
    private WeChatService mWeChatService;

    @Inject
    public WeChatPresenterImpl(WeChatService mWeChatService) {
        this.mWeChatService = mWeChatService;
    }

    @Override
    public void fetchWeChatHot(int num, int page) {
        invoke(mWeChatService.fetchWXHot(AppConstants.KEY_API,num, page), new Callback<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onResponse(WXHttpResponse<List<WXItemBean>> data) {
                List<WXItemBean> newslist = data.getNewslist();
                checkState(newslist);
                mView.refreshView(newslist);
            }
        });
    }

    @Override
    public void fetchWXHotSearch(int num, int page, String word) {
        invoke(mWeChatService.fetchWXHotSearch(AppConstants.KEY_API,num, page, word), new Callback<WXHttpResponse<List<WXItemBean>>>() {
            @Override
            public void onResponse(WXHttpResponse<List<WXItemBean>> data) {
                List<WXItemBean> newslist = data.getNewslist();
                checkState(newslist);
                mView.refreshView(newslist);
            }
        });
    }



}
