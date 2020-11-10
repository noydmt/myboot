package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.MyData;

/*
 * 注意：Repositoryクラスは@SpringBootApplicationが定義されているファイルが属するパッケージ配下に作成すること
 * @SpringBootApplicationが定義されているクラスからパッケージスキャンが行われる為、別パッケージにするとRepositoryのスキャンが行われず
 * Controllerクラスがスキャンされている時点でBean登録できてないことになる
 * 参照：https://teratail.com/questions/159915
 * 今回の例：@SpringBootApplicationが定義されているクラスが属するパッケージ => com.example.demo
 *         Repositoryクラスの属するパッケージ => com.example.demo.repossitory
 */
@Repository // データアクセスのクラスであることを表す
public interface MyDataRepository extends JpaRepository<MyData, Long> {

}
