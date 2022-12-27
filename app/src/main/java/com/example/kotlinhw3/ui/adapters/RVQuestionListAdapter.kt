package com.example.kotlinhw3.ui.adapters

import android.app.Activity
import android.graphics.ColorFilter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.graphics.toColor
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kotlinhw3.MainActivity
import com.example.kotlinhw3.R
import com.example.kotlinhw3.models.QuestionsResponse
import com.example.kotlinhw3.ui.question_with_answers.QWAsFragment
import kotlin.coroutines.coroutineContext

class RVQuestionsListAdapter(
    private val items: ArrayList<QuestionsResponse.Item> = arrayListOf()
): ListAdapter<QuestionsResponse.Item, RVQuestionsListAdapter.QuestionHolder>(DifferentItemCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.question_item, parent, false)
        return QuestionHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun refreshItems(items: List<QuestionsResponse.Item>)
    {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class QuestionHolder(view: View): RecyclerView.ViewHolder(view)
    {
        private val context = view.context
        private val avatar = view.findViewById<ImageView>(R.id.question_item__avatar)
        private val card = view.findViewById<CardView>(R.id.question_item__card)
        private val title = view.findViewById<TextView>(R.id.question_item__title)
        private val text = view.findViewById<TextView>(R.id.question_item__text)
        private val like = view.findViewById<ImageView>(R.id.question_item__like)
        private val likeNumber = view.findViewById<TextView>(R.id.question_item__like_number)
        private val answersCount = view.findViewById<TextView>(R.id.question_item__answers_count)
        private val tags = view.findViewById<TextView>(R.id.question_item__tags)

        fun bind(item: QuestionsResponse.Item)
        {
            title.text = item.title
            text.text = item.text
            likeNumber.text = "0"
            tags.text = "Tags: "

            card.setOnClickListener {
                (context as MainActivity).supportFragmentManager.popBackStack()
                (context as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main, QWAsFragment(item))
                    .addToBackStack(null)
                    .commit()
            }

            like.setOnClickListener {
                like.setColorFilter(context.applicationContext.getColor(R.color.purple_200))
                likeNumber.text = (likeNumber.text.toString().toInt() + 1).toString()
            }
        }
    }

    class DifferentItemCallback: DiffUtil.ItemCallback<QuestionsResponse.Item>()
    {
        override fun areItemsTheSame(oldItem: QuestionsResponse.Item, newItem: QuestionsResponse.Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: QuestionsResponse.Item, newItem: QuestionsResponse.Item): Boolean =
            oldItem == newItem
    }
}