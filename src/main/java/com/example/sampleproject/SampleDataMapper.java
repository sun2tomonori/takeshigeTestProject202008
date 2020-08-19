package com.example.sampleproject;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import java.util.Collection;

@Mapper
public interface SampleDataMapper {
    @Select("SELECT id, val FROM SAMPLE")
    Collection<SampleData> findAll();

    @Select("SELECT id, val FROM SAMPLE WHERE id = #{id}")
    SampleData findById(Long id);

    /**
     * 指定したIDをもつユーザーデータテーブル(user_data)のデータを削除する
     * @param i ID
     */
    @Delete("DELETE FROM SAMPLE WHERE id = #{id}")
    void deleteById(int i);

    /**
     * 指定したユーザーデータテーブル(user_data)のデータを追加する
     * @param userData ユーザーデータテーブル(user_data)の追加データ
     */
    @Insert("INSERT INTO SAMPLE VALUES (#{id}, #{val})")
    void create(SampleData userData);

    /**
     * 指定したユーザーデータテーブル(user_data)のデータを更新する
     * @param userData ユーザーデータテーブル(user_data)の更新データ
     */
    @Update("UPDATE SAMPLE SET val = #{val} WHERE id = #{id}")
    void update(SampleData userData);

    /**
     * ユーザーデータテーブル(user_data)の最大値IDを取得する
     * @return ユーザーデータテーブル(user_data)の最大値ID
     */
    @Select("SELECT NVL(max(id), 0) as maxId FROM SAMPLE")
    long findMaxId();
}