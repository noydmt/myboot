package com.example.demo.repositories;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;

import com.sun.istack.NotNull;

import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity // Entity クラスであることを示す
@Table(name="mydata") // 使用するテーブル名の指定 記述しない場合、クラス名がテーブル名に成りかわる
@NamedQuery(
		name="findWithFreeword",
		query="from mydata where name like :fname or mail like :fmail or memo like :fmemo"
)
public class MyData {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // 自動で値を割り振る、 primaryKey の為
	@Column // カラム名、記述しない場合はフィールド名がカラム名に成りかわる
	@NotNull
	private long id;

	@Column(length = 50, nullable = false) // length : 最大の長さ(文字数) nullable : nullを許可するか
	@NotEmpty(message="名前を入力してください") // 半角全角スペース許容したいから
	private String name;

	@Column(length = 200, nullable = false)
	@NotBlank // 出来るだけスペースを許容したくない (このままだと全角スペースは許容してしまう)
	@Email(message="メールアドレスを入力してください")
	private String mail;

	@Column(length = 3, nullable = false)
	@NotBlank(message="年齢を入力してください")
	@Range(min=0, max=120, message="0歳以上120歳以下") // 年齢はマイナス値はダメ
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
