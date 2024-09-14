package creditcard

import org.junit.Test
import kotlin.test.assertSame

/**
 * クレジットカードのチェックディジットに関するユーティリティのテスト
 *
 * @author menta1k0
 */
class CheckDigitUtilTest {
    private val util = CheckDigitUtil()

    //------------------------------------------------------
    // 引数精査エラーになるパターン
    //------------------------------------------------------

    /** 不正な引数が指定された場合（空文字） */
    @Test(expected = IllegalArgumentException::class)
    fun isValid1(){
        util.isValid("")
    }

    /** 不正な引数が指定された場合（1桁の数字） */
    @Test(expected = IllegalArgumentException::class)
    fun isValid2(){
        util.isValid("1")
    }

    /** 不正な引数が指定された場合（数値以外の文字が含まれている） */
    @Test(expected = IllegalArgumentException::class)
    fun isValid3(){
        util.isValid("123456 ")
    }

    /** 不正な引数が指定された場合（数値以外の文字が含まれている） */
    @Test(expected = IllegalArgumentException::class)
    fun isValid4(){
        util.isValid("1234 56")
    }

    /** 不正な引数が指定された場合（数値以外の文字が含まれている） */
    @Test(expected = IllegalArgumentException::class)
    fun isValid5(){
        util.isValid("1234a56")
    }

    //------------------------------------------------------
    // 引数精査OKとなりチェックディジット算出〜判定に進むパターン
    //------------------------------------------------------
    fun isValid6(){
        // wikipediaの例
        assertSame(true, util.isValid("49927398716"))
    }
}