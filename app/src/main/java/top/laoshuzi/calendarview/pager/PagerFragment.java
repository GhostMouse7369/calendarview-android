package top.laoshuzi.calendarview.pager;

import androidx.recyclerview.widget.LinearLayoutManager;

import top.laoshuzi.calendarview.Article;
import top.laoshuzi.calendarview.ArticleAdapter;
import top.laoshuzi.calendarview.R;
import top.laoshuzi.calendarview.base.fragment.BaseFragment;
import top.laoshuzi.calendarview.group.GroupItemDecoration;
import top.laoshuzi.calendarview.group.GroupRecyclerView;

public class PagerFragment extends BaseFragment {

    private GroupRecyclerView mRecyclerView;


    static PagerFragment newInstance() {
        return new PagerFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pager;
    }

    @Override
    protected void initView() {
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, Article>());
        mRecyclerView.setAdapter(new ArticleAdapter(mContext));
        mRecyclerView.notifyDataSetChanged();
    }

    @Override
    protected void initData() {

    }

    boolean isScrollTop() {
        return mRecyclerView != null && mRecyclerView.computeVerticalScrollOffset() == 0;
    }
}
