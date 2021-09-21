<template>
        <v-layout align-content-space-around justify-start column>
            <message-form :messages="messages" :messageAttr="message" />
            <message-row v-for="message in sortedMessages"
                :key="message.id"
                :message="message"
                :editMessage="editMessage"
                :deleteMessage="deleteMessage"
                :messages="messages" />
        </v-layout>
</template>

<script>
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import messagesApi from 'api/messages'

    export default {
        props: ['messages'],
        data() {
            return {
                message: null
            }
        },
        components: {
            MessageRow,
            MessageForm
        },
        computed: {
            sortedMessages() {
                return this.messages.sort((a, b) => -(a.id - b.id))
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {
                messagesApi.remove(message.id).then(result => {
                    if (result.ok) {
                        // const a = this.messages.indexOf(message)
                        // this.messages.splice(a, 1)
                    }
                })
            }
        }
    }
</script>

<style>

</style>