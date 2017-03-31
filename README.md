# Application
RecyclerView快速开发框架
1.单一格式的列表：

 mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        for(int i = 1; i <= 50; i ++) {
            User user = new User();
            user.setAge(i + 10);
            user.setName("张三" + i);
            userList.add(user);
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
       QuickAdapter adapter = new QuickAdapter<User>(userList, this, R.layout.layout_item, null) {

            @Override
            public void onBindData(QuickHolder holder, int position, User user) {
                holder.setText(R.id.test_tv, user.getName()).setText(R.id.text, user.getAge() + "");
            }
        };
        mRecyclerView.setAdapter(adapter);
2.如果你需要添加头布局和尾布局，只需要添加如下代码：

  adapter.addFoofer(R.layout.layout_footer);
  adapter.addHeader(R.layout.layout_header);
为header和footer设置数据，支持链式调用：

            @Override
            public void onBindFooter(QuickHolder holder) {
                super.onBindFooter(holder);
                holder.setText(R.id.footer_tv, R.layout.layout_footer, "小尾巴~~~");
            }

            @Override
            public void onBindHeader(QuickHolder holder) {
                super.onBindHeader(holder);
                holder.setText(R.id.header_tv, R.layout.layout_header, "头布局").setImage(R.id.header_iv, R.layout.layout_header, R.mipmap.shopdetail_bg);
            }
3.除了头布局和尾布局，还支持其他不同类型item：

           @Override
            public int getViewType(int position) {
                if(position % 5 == 0) {
                    return 1001;
                } else {
                    return 1002;
                }
            }

            @Override
            public int getItemViewId(int viewType) {
                if(viewType == 1001) {
                    return R.layout.layout_test;
                }
                return R.layout.layout_item;
            }
同样，在绑定数据的时候，需要同时专递layoutid和viewid：

holder.setText(R.id.test_tv, user.getName()).setText(R.id.text, user.getAge() + "");
holder.setText(R.id.top_tv, R.layout.layout_test, user.getName()).setImage(R.id.top_iv, R.layout.layout_test, R.mipmap.ic_launcher);
除此以外，还有其他特性。总体概括如下：
1.API使用简单、快捷
2.支持头布局、尾布局，和其他多种item
3.支持链式调用
4.gridLayoutManager情况下，默认头布局和尾布局占据整行（需要传递gridLayoutManager到适配器中）
5.view通过sparseArray进行缓存，最大程度的节约内存
6.支持item点击事件

｛ 注意，由于id和控件映射缓存，所以不要出现id相同的情况｝
