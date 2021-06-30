package com.iogogogo.supervisord.util;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by tao.zeng on 2021/6/25.
 */
public interface ListUtils {

    /**
     * Partition page wrapper.
     *
     * @param <T>      the type parameter
     * @param pageNo   the page no
     * @param pageSize the page size
     * @param records  the records
     * @return the page wrapper
     */
    static <T> PageWrapper<T> partition(int pageNo, int pageSize, List<T> records) {
        if (pageNo <= 0) pageNo = 1;

        // 计算偏移量
        int offset = (pageNo - 1) * pageSize;

        // 总算总记录数
        int totalSize = records.size();

        if (offset > totalSize) return new PageWrapper<>(pageNo, pageSize, totalSize, Collections.emptyList());

        // 分页结果数据
        List<T> collect = records.stream().skip(offset).limit(pageSize).collect(Collectors.toList());

        return new PageWrapper<>(pageNo, pageSize, totalSize, collect);
    }



    /* *
     public static void main(String[] args) {
        int totalSize = 11;//数据总量
        int pageSize = 3;//一页显示条数
        int totalPage;//总页数

        // 几种页数计算方法
        totalPage = totalSize / pageSize;
        if (totalSize % pageSize != 0) {
            totalPage++;
        }
        System.out.println(totalPage);//此方法容易理解
        System.out.println((totalSize - 1) / pageSize + 1);//此方法使用较多
        System.out.println((totalSize + pageSize - 1) / pageSize);
    }
    * */

    /**
     * The type Page wrapper.
     *
     * @param <T> the type parameter
     */
    @Data
    class PageWrapper<T> implements Serializable {
        /**
         * 当前页，每页显示size
         */
        private int pageNo, pageSize;

        /**
         * 总记录数，总页数
         */
        private long totalSize, totalPage;

        /**
         * 分页结果数据
         */
        private List<T> records;

        /**
         * Instantiates a new Page wrapper.
         *
         * @param pageNo    the page no
         * @param pageSize  the page size
         * @param totalSize the total size
         * @param records   the records
         */
        public PageWrapper(int pageNo, int pageSize, long totalSize, List<T> records) {
            this.setPageNo(pageNo);
            this.pageSize = pageSize;
            this.records = records;
            this.totalSize = totalSize;
            this.totalPage = (totalSize - 1) / pageSize + 1;
        }

        /**
         * Sets page no.
         *
         * @param pageNo the page no
         */
        public void setPageNo(int pageNo) {
            if (pageNo <= 0) this.pageNo = 1;
            this.pageNo = pageNo;
        }
    }
}
