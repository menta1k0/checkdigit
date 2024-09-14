package creditcard

/**
 * クレジットカードのチェックディジットに関するユーティリティ
 *
 * Luhnアルゴリズム
 * モジュラス10 ウェイト2・1分割
 *
 * @author menta1k0
 */
class CheckDigitUtil {
    /**
     * クレジットカード番号の体系が正しいかを判定する。
     * チェックディジットが不正な場合にfalseを返却する。
     *
     * @param pan クレジットカード番号(チェックディジット付)
     * @return 有効なチェックディジットを持つ場合にtrue、そうでない場合にfalseを返す
     * @throws IllegalArgumentException 引数panに不備がある場合
     */
    fun isValid(pan: String): Boolean{
        //----------------------------
        // 引数精査
        //----------------------------
        // substringの前の精査
        if(pan.length < 2){
            throw IllegalArgumentException("panの桁数が短すぎます（pan=[$pan]）")
        }

        // 数値精査
        try{
            pan.toLong()
        }catch (e: Exception){
            throw IllegalArgumentException("panに数値以外の文字が含まれています（pan=[$pan]）")
        }

        //----------------------------
        // チェックディジットの算出
        //----------------------------
        // チェックディジット以外の部分
        val panSeq = pan.substring(0, pan.length - 1)
        // チェックディジット
        val cd = pan.substring(pan.length - 1, pan.length)

        return cd == calculateCheckDigit(panSeq)
    }

    /**
     * チェックディジットを算出する。
     *
     *
     * @param input チェックディジットを算出する対象の文字列
     * @return チェックディジット
     * @throws IllegalArgumentException 引数inputに不備がある場合
     */
    fun calculateCheckDigit(input: String): String{
        //----------------------------
        // 引数精査
        //----------------------------
        // 数値精査
        try{
            input.toLong()
        }catch (e: Exception){
            throw IllegalArgumentException("inputに数値以外の文字が含まれています（input=[$input]）")
        }

        //----------------------------
        // チェックディジットの算出
        //----------------------------
        val charList = input.toList().reversed()
        var total = 0
        var multi = true
        for(c in charList){
            if(multi){
                val i = Integer.parseInt(c.toString()) * 2
                if(i > 9){
                    total += ( i - 9 )
                }else{
                    total += i
                }
                multi = false
            }else{
                total += Integer.parseInt(c.toString())
                multi = true
            }
        }

        val mod = total % 10
        return (10 - mod).toString()
    }
}