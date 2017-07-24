App handles banking transactions.

Contents:
App loads accounts, transactions to queue and processes them. Transactions are implemented with Command pattern.
Contains:
	- accounts' map (consists clinent id, account number and balance)
	- classes to administrate and process transaction queue
	- transaction queue made with Singleton pattern

Considerations:
	- Client can not have debit on his account
	- If transaction can not be handled due to too small amount on client's account, it goes on end of queue to be hanled again
	- If transaction is processed again and it still can't be handled, is saved on unhandled transactions' list
	- assume that all  transactions are internal and all accounts exist

Account Observer appends to account history .txt file: acc id, date, time and balance every time, when balance changes.

Transfer Observer appends to transfer_history.txt file: transfer id, date, time and transfer status.

FinishedTransfer Observer appends to finished_transfer.txt: transfer id, source_acc number, targer_acc number, amount, date and time every time, when transfer status changes to "finished".



Input: two text files, one with accounts, second with transactions to be processed.
Account line: {ID} {ACC_NUMBER} {BALANCE}
Example: 1 1000010000 359.22
Transaction line: {SOURCE_ACC_NUMBER} {TARGET_ACC_NUMBER} {AMOUNT}
Example: 10001000 20002000 300.21

Output: .txt files with balance history for every account, list of  finished transfers and transfers history.
