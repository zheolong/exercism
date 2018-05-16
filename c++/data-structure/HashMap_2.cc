/*
 * 蚂蚁金服面试题
 * 一）用c/c++实现一个hash_map，提供构造函数，析构函数，insert，key为int类型，value类型为char[16]
 */

#include<iostream>

const int TSIZE = 1024;
const int VLEN = 16;

struct HashNode
{
    int key;
    char value[VLEN];
    HashNode* next;

    HashNode(int key, char value[VLEN])
    {
        this->next = NULL;
        this->key = key;
        memcpy(this->value, value, VLEN);
    }
};
 
class HashMap
{
    public:
        HashMap();
        ~HashMap();
        void insert(int key, char value[VLEN]);
        bool find(int key);

    private:
        HashNode** hash_table;
        int hash_func(int key);

};

HashMap::HashMap()
{
    hash_table = new HashNode*[TSIZE];
    for (int i = 0; i < TSIZE; i++)
        hash_table[i] = NULL;
}

HashMap::~HashMap()
{
    for (int i = 0; i < TSIZE; ++i) {
        HashNode* elem = hash_table[i];
        while (elem != NULL) {
            HashNode* p = elem;
            elem = elem->next;
            delete p;
        }
    }

    delete[] hash_table;
}

int HashMap::hash_func(int key)
{
    return key % TSIZE;
}

void HashMap::insert(int key, char value[VLEN])
{
    int hash_value = hash_func(key);
    HashNode* p = NULL;
    HashNode* elem = hash_table[hash_value];

    while (elem != NULL) {
        p = elem;
        elem = elem->next;
    }

    if (elem != NULL) {
        memcpy(elem->value, value, VLEN);
    } else {
        elem = new HashNode(key, value);
        if (p != NULL) {
            p->next = elem;
        } else {
            hash_table[hash_value] = elem;
        }
    }
}

bool HashMap::find(int key)
{
    int hash_value = hash_func(key);
    HashNode* elem = hash_table[hash_value];

    while (elem != NULL) {
        if (elem->key == key) {
            std::cout << "Value is: " << elem->value << std::endl;
            return true;
        }
        elem = elem->next;
    }

    return false;
}

int main()
{
    HashMap hash;
    int key;
    char value[VLEN] = "I am qjl";

    key = 1;
    hash.insert(key, value);
    if (hash.find(key)) {
        std::cout <<"Found with key "<< key<< std::endl;
    } else {
        std::cout <<"Not found with key "<< key << std::endl;
    }

    key = 2;
    if (hash.find(key)) {
        std::cout <<"Found with key "<< key<< std::endl;
    } else {
        std::cout <<"Not found with key "<< key << std::endl;
    }

    return 0;
}
