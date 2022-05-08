<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Member extends Model
{
    use HasFactory;

    protected $fillable =['user_id','club_id'];

    public function club()
    {
        return $this->belongsTo(club::class);
    }

    public function user()
    {
        return $this->belongsTo(User::class);
    }
}
