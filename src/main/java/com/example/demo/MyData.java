package com.example.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity // Entity クラスであることを示す
@Table(name="mydata") // 使用するテーブル名の指定 記述しない場合、クラス名がテーブル名に成りかわる
public class MyData {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // 自動で値を割り振る、 primaryKey の為
	@Column // カラム名、記述しない場合はフィールド名がカラム名に成りかわる
	private long id;

	@Column(length = 50, nullable = false) // length : 最大の長さ(文字数) nullable : nullを許可するか
	private String name;

	@Column(length = 200, nullable = true)
	private String mail;

	@Column(length = 3, nullable = true)
	private String age;

	@Column(nullable = true)
	private String memo;

	/**
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id セットする id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail セットする mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @return age
	 */
	public String getAge() {
		return age;
	}

	/**
	 * @param age セットする age
	 */
	public void setAge(String age) {
		this.age = age;
	}

	/**
	 * @return memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo セットする memo
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
