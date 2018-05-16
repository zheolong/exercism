#/usr/bin/env ruby

answer = rand(10)

loop {
    guess = gets().to_i
    
    if guess == answer
        puts "Bingo !!!"
        break
    elsif guess < answer
        puts "Too Small"
    else
        puts "Too Large"
    end
}

puts "Game Over"
