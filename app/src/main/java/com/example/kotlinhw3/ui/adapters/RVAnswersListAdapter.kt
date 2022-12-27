package com.example.kotlinhw3.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhw3.R
import com.example.kotlinhw3.models.AnswersResponse
import kotlinx.coroutines.handleCoroutineException

class RVAnswersListAdapter(
    private val items: ArrayList<AnswersResponse.Item> = arrayListOf()
): ListAdapter<AnswersResponse.Item, RVAnswersListAdapter.AnswerHolder>(DifferentItemCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.answer_item, parent, false)
        return AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int)
    {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun refreshItems(items: List<AnswersResponse.Item>)
    {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    class AnswerHolder(view: View): RecyclerView.ViewHolder(view)
    {
        private val context = view.context
        private val avatar = view.findViewById<ImageView>(R.id.answer_item__avatar)
        private val text = view.findViewById<TextView>(R.id.answer_item__text)
        private val like = view.findViewById<ImageView>(R.id.answer_item__like)
        private val likeNumber = view.findViewById<TextView>(R.id.answer_item__like_number)
        private val correct = view.findViewById<CheckBox>(R.id.answer_item__correct)

        fun bind(item: AnswersResponse.Item)
        {
            text.text = item.text
            likeNumber.text = "0"
            correct.isChecked = item.status

            like.setOnClickListener {
                like.setColorFilter(context.applicationContext.getColor(R.color.purple_200))
                likeNumber.text = (likeNumber.text.toString().toInt() + 1).toString()
            }
        }
    }

    class DifferentItemCallback: DiffUtil.ItemCallback<AnswersResponse.Item>()
    {
        override fun areItemsTheSame(oldItem: AnswersResponse.Item, newItem: AnswersResponse.Item): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: AnswersResponse.Item, newItem: AnswersResponse.Item): Boolean =
            oldItem == newItem
    }
}