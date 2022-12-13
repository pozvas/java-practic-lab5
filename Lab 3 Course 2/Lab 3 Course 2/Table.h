#pragma once
template<typename T1, typename T2>
class Table
{
private:
	T1* Keys;
	T2* Values;
	size_t size;
	size_t curInd;
public:
	Table(size_t _size) :size(_size), curInd(0) {
		Keys = new T1[size];
		Values = new T2[size];
	}
	void Apennd(const T1& key, const T2& value) {
		if (curInd >= size) {
			return;
		}
		Keys[curInd] = key;
		Values[curInd++] = value;
	}
	const T2& operator[](const T1& key) const {
		for (size_t i = 0; i < size; i++) {
			if (Keys[i] == key)
				return Values[i];
		}
		throw "Not found Exception";
	}
	T2& operator[](const T1& key) {
		for (size_t i = 0; i < size; i++) {
			if (Keys[i] == key)
				return Values[i];
		}
		throw "Not found Exception";
	}
	~Table() {
		delete[] Key;
		delete[] Values;
		Keys = nullptr;
		Values = nullptr;
	}
};