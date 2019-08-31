# coding: utf-8

from bs4 import BeautifulSoup
import re, urllib3, string, json
from time import sleep
from IPython.display import clear_output

def get_data(url):
    r = http.request('GET', url)
    if r.status == 200: 
        # clear_output()
        print('Joy! Able to scrape', url)
        soup = BeautifulSoup(r.data, 'html.parser')
        return soup
    else:
        print('Oops, issue:', r.status)
        print('Url:', url)
        print('-'*60)

def get_words(soup):
    words = []
    for item in soup.find_all('table', class_='hometab')[0].find_all('a'):
        words.append(item.text)
    words.sort()
    return words

def next_link(soup):
    next_page = '/images/portail/right.gif'
    link_img = soup.find('img', src=next_page)
    if link_img is not None:
        new_link = link_img.find_parent()
        new_link = cnrtl_url + new_link.get('href')
        return new_link
    else:
        print('Done.')
        return 

def scraper(url):
    sleep(.5)
    soup = get_data(url)
    words = get_words(soup)
    new_url = next_link(soup)
    return words, new_url


if __name__ == '__main__':
    
    cnrtl_url = 'http://www.cnrtl.fr'
    cnrtl_blurb = '/portailindex/LEXI/'
    cnrtl_dicts = [
                    ('cnrtl', 'TLFI'), 
                    ('acad9', 'ACA9'), 
                    ('acad8', 'ACA8'),
                    ('acad4', 'ACA4'), 
                    ('francophonie', 'FRAN'), 
                    ('bhvf', 'BHVF'), 
                    ('dmf', 'ADMF')
                   ]

    http = urllib3.PoolManager()
    
    my_dicts = []

    for item in [cnrtl_dicts[0], cnrtl_dicts[6]]:
        word_list = []
        for letter in string.ascii_uppercase:
            url = cnrtl_url + cnrtl_blurb + item[1] + '/' + letter
            while url:
                words, url = scraper(url)
                word_list.extend(words)

        my_dicts.append(word_list)        
        print('Number of entries in ', item[0], ':', len(word_list))

        with open(item[0] + '.txt', 'w') as txt:
            for word in word_list:
                txt.write(word)
                txt.write('\n')

        with open(item[0] + '.json', 'w') as txt:
            json.dump(word_list, txt)

