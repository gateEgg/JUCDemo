package com.example.jucdemo.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author jiesi
 * @Description Stream 流式计算
 * @Date 2021/9/14 11:31 上午
 */
public class StreamDemo {
    public static void main(String[] args) {
        User a = User.builder().id(1).username("a").age(11).build();
        User b = User.builder().id(2).username("b").age(22).build();
        User c = User.builder().id(3).username("c").age(33).build();
        User d = User.builder().id(4).username("d").age(25).build();
        User e = User.builder().id(5).username("e").age(15).build();

        List<User> users = Arrays.asList(a, b, c, d, e);

        users.stream()
                .filter(u -> (u.getId() & 1) != 0)
                .filter(u -> u.getAge() > 11)
                .map(u -> u.getUsername().toUpperCase())
//                .sorted((w1, w2) -> w2.compareTo(w1))
                .sorted(Comparator.reverseOrder())
                .limit(1)
                .forEach(System.out::println);
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class User {
    private Integer id;
    private String username;
    private Integer age;
}
