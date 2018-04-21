package br.com.franco.lucas.bancoNeon.model

import android.os.Parcel
import android.os.Parcelable
import java.util.ArrayList

/**
 * Created by lucas on 2/25/18.
 */
class Person(var id: Long,
             var name: String,
             var phone: String,
             var avatar: String):Parcelable{


    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    companion object CREATOR : Parcelable.Creator<Person>{
        fun returnMockedData() : ArrayList<Person>{

            //Sei que poderia usar algum tipo de dataFaker, ou alguma forma com menos código,
            // mas gosto desses personagens em específico ;D

            val returnArray = ArrayList<Person>()
            returnArray.add(Person(1,"Legolas Greenleaf","(16)991239-1029","https://vignette.wikia.nocookie.net/lotr/images/3/33/Legolas_-_in_Two_Towers.PNG/revision/latest?cb=20120916035151"))
            returnArray.add(Person(2,"Gimli Filho de Glóin","(16)993219-5031","http://middle-earthencyclopedia.weebly.com/uploads/3/8/2/9/3829140/1925657.png?316"))
            returnArray.add(Person(3,"Gandalf O Cinzento","(16)99029-1127","https://vignette.wikia.nocookie.net/lotr/images/e/e7/Gandalf_the_Grey.jpg/revision/latest?cb=20121110131754"))
            returnArray.add(Person(4,"Aragorn Filho de Arathorn","(16)99129-1309","https://upload.wikimedia.org/wikipedia/en/thumb/3/35/Aragorn300ppx.png/150px-Aragorn300ppx.png"))
            returnArray.add(Person(5,"Frodo Bolseiro","(16)95849-1302","https://cdn.costumewall.com/wp-content/uploads/2017/06/frodo-baggins.jpg"))
            returnArray.add(Person(6,"Jon Snow","(11)99329-1582","https://www.thesun.co.uk/wp-content/uploads/2017/05/jon-snow-and-the-nights-watch-e1494021230137.jpg?strip=all&w=960"))
            returnArray.add(Person(7,"Cersei Lannister","(11)95673-1960","https://vignette.wikia.nocookie.net/gameofthrones/images/2/2a/Cersei-queen.png/revision/latest?cb=20160725184739&path-prefix=pt-br"))
            returnArray.add(Person(8,"Daenerys Targaryen","(11)93458-3387","https://upload.wikimedia.org/wikipedia/pt/thumb/f/f6/GOT-CinePOP-750x380.jpg/230px-GOT-CinePOP-750x380.jpg"))
            returnArray.add(Person(9,"Ned Stark","(11)93984-3484","https://upload.wikimedia.org/wikipedia/pt/thumb/5/52/Ned_Stark-Sean_Bean.jpg/220px-Ned_Stark-Sean_Bean.jpg"))
            returnArray.add(Person(10,"Robert Baratheon","(11)91239-1197","https://vignette.wikia.nocookie.net/gameofthrones/images/d/d4/RobertBaratheon.jpg/revision/latest?cb=20141119000127"))
            returnArray.add(Person(11,"Rick Sanchez","(19)94897-1349","https://vignette.wikia.nocookie.net/rickandmorty/images/8/8f/Rickk22.png/revision/latest?cb=20170911060616"))
            returnArray.add(Person(12,"Morty Smith","(19)91437-1402","https://vignette.wikia.nocookie.net/rickandmorty/images/4/41/Morty_Smith.jpg/revision/latest?cb=20170217193441"))
            returnArray.add(Person(13,"Jerry Smith","(19)99503-3405","https://vignette.wikia.nocookie.net/rickandmorty/images/f/f1/Jerry_Smith.png/revision/latest?cb=20160923151111"))
            returnArray.add(Person(14,"Beth Sanchez","(19)93405-5973","https://vignette.wikia.nocookie.net/rickandmorty/images/5/58/Beth_Smith.png/revision/latest?cb=20151204220729"))
            returnArray.add(Person(15,"Summer Smith","(19)99029-1029","https://vignette.wikia.nocookie.net/rickandmorty/images/a/ad/Summer_is_cool.jpeg/revision/latest/scale-to-width-down/310?cb=20160919183158"))

            return returnArray
        }

        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }

    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(name)
        parcel.writeString(phone)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

}