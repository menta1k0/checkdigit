package creditcard

import org.junit.Test
import kotlin.test.assertEquals

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
    /** wikipediaの例 */
    @Test
    fun isValid6(){
        assertEquals(true, util.isValid("49927398716"))
    }

    /** 余りが10=チェックディジット0となる場合のテストケース */
    @Test
    fun calculateCheckDigit1(){
        assertEquals("0", util.calculateCheckDigit("19"))
        assertEquals("0", util.calculateCheckDigit("24"))
        assertEquals("0", util.calculateCheckDigit("38"))
        assertEquals("0", util.calculateCheckDigit("43"))
        assertEquals("0", util.calculateCheckDigit("57"))
        assertEquals("0", util.calculateCheckDigit("62"))
        assertEquals("0", util.calculateCheckDigit("76"))
    }

    /** 余り0以外の既存機能確認用のケース */
    @Test
    fun calculateCheckDigit2(){
        assertEquals("1", util.calculateCheckDigit("900"))
        assertEquals("9", util.calculateCheckDigit("901"))
        assertEquals("7", util.calculateCheckDigit("902"))
        assertEquals("5", util.calculateCheckDigit("903"))
        assertEquals("3", util.calculateCheckDigit("904"))
        assertEquals("0", util.calculateCheckDigit("905"))
        assertEquals("8", util.calculateCheckDigit("906"))
        assertEquals("6", util.calculateCheckDigit("907"))
        assertEquals("4", util.calculateCheckDigit("908"))
        assertEquals("2", util.calculateCheckDigit("909"))
        assertEquals("0", util.calculateCheckDigit("910"))
        assertEquals("8", util.calculateCheckDigit("911"))
        assertEquals("6", util.calculateCheckDigit("912"))
        assertEquals("4", util.calculateCheckDigit("913"))
        assertEquals("2", util.calculateCheckDigit("914"))
        assertEquals("9", util.calculateCheckDigit("915"))
        assertEquals("7", util.calculateCheckDigit("916"))
        assertEquals("5", util.calculateCheckDigit("917"))
        assertEquals("3", util.calculateCheckDigit("918"))
        assertEquals("1", util.calculateCheckDigit("919"))
    }
}