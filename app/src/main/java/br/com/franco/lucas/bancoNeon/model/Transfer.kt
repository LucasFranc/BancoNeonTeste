package br.com.franco.lucas.bancoNeon.model

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import java.text.DecimalFormat

/**
 * Created by lucas on 2/26/18.
 */
class Transfer(
            val Id: Long,
            val ClienteId: Long,
            val Valor: Double,
            val Token: String,
            val Data: String,
            val person: Person) : Parcelable{

    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readLong(),
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Person::class.java.classLoader))

    companion object CREATOR : Parcelable.Creator<Transfer>{

        fun formatTransferData(transfers: ArrayList<Transfer>): ArrayList<Transfer> {
            val returnList = ArrayList<Transfer>()
            val people = Person.returnMockedData()

            for (t in transfers) {
                for (p in people) {
                    if(t.ClienteId == p.id) {
                        val aux = Transfer(t.Id, t.ClienteId, t.Valor, t.Token, t.Data, p)
                        returnList.add(0,aux)
                    }
                }
            }
            return returnList
        }

        fun groupTransfers(transfers: ArrayList<Transfer>) : ArrayList<MoneySum>{

            val resultList = transfers.groupBy({ it.person.id},{it.Valor})
            val sumList = ArrayList<MoneySum>()

            for (s in resultList.entries){
                for(t in transfers) {
                    if(t.ClienteId == s.key) {
                        val moneySum = MoneySum(s.value.sum(), s.key)
                        moneySum.avatar = t.person.avatar
                        sumList.add(moneySum)
                        break
                    }
                }
            }
            return sumList
        }


        override fun createFromParcel(parcel: Parcel): Transfer {
            return Transfer(parcel)
        }

        override fun newArray(size: Int): Array<Transfer?> {
            return arrayOfNulls(size)
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(Id)
        parcel.writeLong(ClienteId)
        parcel.writeDouble(Valor)
        parcel.writeString(Token)
        parcel.writeString(Data)
        parcel.writeParcelable(person, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

}